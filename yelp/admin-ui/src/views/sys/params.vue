<template>
  <div class="mod-sys__params">
    <el-form :inline="true" :model="state.dataForm" @keyup.enter="state.getDataList()">
      <el-form-item>
        <el-input v-model="state.dataForm.paramCode" placeholder="Encoding" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="state.getDataList()">SEARCH</el-button>
      </el-form-item>
      <el-form-item>
        <el-button v-if="state.hasPermission('sys:params:save')" type="primary" @click="addOrUpdateHandle()">ADD</el-button>
      </el-form-item>
      <el-form-item>
        <el-button v-if="state.hasPermission('sys:params:delete')" type="danger" @click="state.deleteHandle()">DELETE</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="state.dataListLoading" :data="state.dataList" border @selection-change="state.dataListSelectionChangeHandle" style="width: 100%">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="paramCode" label="Encoding" header-align="center" align="center"></el-table-column>
      <el-table-column prop="paramValue" label="value" header-align="center" align="center"></el-table-column>
      <el-table-column prop="remark" label="Note" header-align="center" align="center"></el-table-column>
      <el-table-column label="PROCESS" fixed="right" header-align="center" align="center" width="150">
        <template v-slot="scope">
          <el-button v-if="state.hasPermission('sys:params:update')" type="primary" link @click="addOrUpdateHandle(scope.row.id)">EDIT</el-button>
          <el-button v-if="state.hasPermission('sys:params:delete')" type="primary" link @click="state.deleteHandle(scope.row.id)">DELETE</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :current-page="state.page" :page-sizes="[10, 20, 50, 100]" :page-size="state.limit" :total="state.total" layout="total, sizes, prev, pager, next, jumper" @size-change="state.pageSizeChangeHandle" @current-change="state.pageCurrentChangeHandle"> </el-pagination>
    <!-- 弹窗, ADD / EDIT -->
    <add-or-update ref="addOrUpdateRef" @refreshDataList="state.getDataList"></add-or-update>
  </div>
</template>

<script lang="ts" setup>
import useView from "@/hooks/useView";
import { reactive, ref, toRefs } from "vue";
import AddOrUpdate from "./params-add-or-update.vue";

const view = reactive({
  getDataListURL: "/sys/params/page",
  getDataListIsPage: true,
  deleteURL: "/sys/params",
  deleteIsBatch: true,
  dataForm: {
    paramCode: ""
  }
});

const state = reactive({ ...useView(view), ...toRefs(view) });

const addOrUpdateRef = ref();
const addOrUpdateHandle = (id?: number) => {
  addOrUpdateRef.value.init(id);
};
</script>

<template>
  <div class="mod-demo__report">
    <el-form :inline="true" :model="state.dataForm" @keyup.enter="state.getDataList()">
<!--      <el-form-item>-->
<!--        <el-button v-if="state.hasPermission('demo:report:save')" type="primary" @click="addOrUpdateHandle()">ADD</el-button>-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button v-if="state.hasPermission('demo:report:delete')" type="danger" @click="state.deleteHandle()">DELETE</el-button>-->
<!--      </el-form-item>-->
    </el-form>
    <el-table v-loading="state.dataListLoading" :data="state.dataList" border @selection-change="state.dataListSelectionChangeHandle" style="width: 100%">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="id" label="ID" header-align="center" align="center" width="180"></el-table-column>
<!--      <el-table-column prop="discussId" label="DISCUSS ID" header-align="center" align="center" width="120"></el-table-column>-->
<!--      <el-table-column prop="reportUserId" label="REPORT USER ID" header-align="center" align="center" width="200"></el-table-column>-->
      <el-table-column prop="reportUserName" label="Informer" header-align="center" align="center" width="150"></el-table-column>
<!--      <el-table-column prop="reportType" label="REPORT TYPE" header-align="center" align="center" width="150"></el-table-column>-->
      <el-table-column prop="reportContent" label="Report Content" header-align="center" align="center" width="300"></el-table-column>
      <el-table-column prop="attr2" label="Informant" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="attr1" label="Reported Comments" header-align="center" align="center" width="300"></el-table-column>
<!--      <el-table-column prop="reportHandleType" label="HANDLING TYPE" header-align="center" align="center" width="150"></el-table-column>-->
      <el-table-column prop="reportHandle" label="Processing Result" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="audit" label="Audit Status" header-align="center" align="center" width="150">
        <template v-slot="scope">
          {{ scope.row.audit === "1" ? "PASS" : (scope.row.audit === "0" ? "FAIL" : "") }}
        </template>
      </el-table-column>
      <el-table-column prop="auditContent" label="Audit Content" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="createDate" label="Create Date" header-align="center" align="center"  width="200"></el-table-column>
      <el-table-column prop="updateDate" label="Update Date" header-align="center" align="center"  width="200"></el-table-column>
    <el-table-column label="PROCESS" fixed="right" header-align="center" align="center" width="200">
      <template v-slot="scope">
        <el-button v-if="state.hasPermission('demo:report:update')" type="primary" @click="addOrUpdateHandle(scope.row.id)">audit</el-button>
        <el-button v-if="state.hasPermission('demo:report:delete')" type="danger"  @click="state.deleteHandle(scope.row.id)">delete</el-button>
      </template>
    </el-table-column>
    </el-table>
    <el-pagination :current-page="state.page" :page-sizes="[10, 20, 50, 100]" :page-size="state.limit" :total="state.total" layout="total, sizes, prev, pager, next, jumper" @size-change="state.pageSizeChangeHandle" @current-change="state.pageCurrentChangeHandle"> </el-pagination>
    <!-- 弹窗, ADD / EDIT -->
    <add-or-update ref="addOrUpdateRef" @refreshDataList="state.getDataList">CONFIRM</add-or-update>
  </div>
</template>

<script lang="ts" setup>
import useView from "@/hooks/useView";
import { reactive, ref, toRefs } from "vue";
import AddOrUpdate from "./report-add-or-update.vue";

const view = reactive({
  deleteIsBatch: true,
  getDataListURL: "/demo/report/page",
  getDataListIsPage: true,
  exportURL: "/demo/report/export",
  deleteURL: "/demo/report"
});

const state = reactive({ ...useView(view), ...toRefs(view) });

const addOrUpdateRef = ref();
const addOrUpdateHandle = (id?: number) => {
  addOrUpdateRef.value.init(id);
};
</script>

<template>
  <div class="mod-sys__log-operation">
    <el-form :inline="true" :model="state.dataForm" @keyup.enter="state.getDataList()">
      <el-form-item>
        <el-select v-model="state.dataForm.status" placeholder="STATUS" clearable>
          <el-option label="FAILED" :value="0"></el-option>
          <el-option label="SUCCESS" :value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="state.getDataList()">SEARCH</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="state.dataListLoading" :data="state.dataList" border @sort-change="state.dataListSortChangeHandle" style="width: 100%">
      <el-table-column prop="creatorName" label="USERNAME" header-align="center" align="center"></el-table-column>
<!--      <el-table-column prop="operation" label="PROCESS" header-align="center" align="center"></el-table-column>-->
      <el-table-column prop="requestUri" label="URI" header-align="center" align="center" show-overflow-tooltip></el-table-column>
      <el-table-column prop="requestMethod" label="REQUEST" header-align="center" align="center"></el-table-column>
      <el-table-column prop="requestParams" label="PARAMS" header-align="center" align="center" width="150" show-overflow-tooltip></el-table-column>
      <el-table-column prop="requestTime" label="LONG" sortable="custom" header-align="center" align="center">
        <template v-slot="scope">
          {{ `${scope.row.requestTime}ms` }}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="STATUS" sortable="custom" header-align="center" align="center">
        <template v-slot="scope">
          <el-tag v-if="scope.row.status === 0" size="small" type="danger">FAILED</el-tag>
          <el-tag v-else size="small" type="success">SUCCESS</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="ip" label="IP" header-align="center" align="center" show-overflow-tooltip></el-table-column>
      <el-table-column prop="userAgent" label="PROXY" header-align="center" align="center" width="150" show-overflow-tooltip></el-table-column>
      <el-table-column prop="createDate" label="CREATETIME" sortable="custom" header-align="center" align="center" width="180"></el-table-column>
    </el-table>
    <el-pagination :current-page="state.page" :page-sizes="[10, 20, 50, 100]" :page-size="state.limit" :total="state.total" layout="total, sizes, prev, pager, next, jumper" @size-change="state.pageSizeChangeHandle" @current-change="state.pageCurrentChangeHandle"> </el-pagination>
  </div>
</template>

<script lang="ts" setup>
import useView from "@/hooks/useView";
import { reactive, toRefs } from "vue";

const view = reactive({
  getDataListURL: "/sys/log/operation/page",
  getDataListIsPage: true,
  exportURL: "/sys/log/operation/export",
  dataForm: {
    status: ""
  }
});

const state = reactive({ ...useView(view), ...toRefs(view) });
</script>

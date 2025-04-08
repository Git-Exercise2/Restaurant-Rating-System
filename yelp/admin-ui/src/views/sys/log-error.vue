<template>
  <div class="mod-sys__log-error">
    <el-table v-loading="state.dataListLoading" :data="state.dataList" border @sort-change="state.dataListSortChangeHandle" style="width: 100%">
      <el-table-column prop="requestUri" label="URI" header-align="center" align="center"></el-table-column>
      <el-table-column prop="requestMethod" label="REQUEST" header-align="center" align="center"></el-table-column>
      <el-table-column prop="requestParams" label="PARAMS" header-align="center" align="center" width="150" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="ip" label="IP" header-align="center" align="center"></el-table-column>
      <el-table-column prop="userAgent" label="PROXY" header-align="center" align="center" width="150" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="createDate" label="CREATETIME" sortable="custom" header-align="center" align="center" width="180"></el-table-column>
      <el-table-column label="PROCESS" fixed="right" header-align="center" align="center" width="150">
        <template v-slot="scope">
          <el-button type="primary" link @click="infoHandle(scope.row.errorInfo)">Exception information</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :current-page="state.page" :page-sizes="[10, 20, 50, 100]" :page-size="state.limit" :total="state.total" layout="total, sizes, prev, pager, next, jumper" @size-change="state.pageSizeChangeHandle" @current-change="state.pageCurrentChangeHandle"> </el-pagination>
  </div>
</template>

<script lang="ts" setup>
import useView from "@/hooks/useView";
import { reactive, toRefs } from "vue";
import { ElMessageBox } from "element-plus";

const view = reactive({
  getDataListURL: "/sys/log/error/page",
  getDataListIsPage: true,
  exportURL: "/sys/log/error/export"
});

const state = reactive({ ...useView(view), ...toRefs(view) });

const infoHandle = (info: string) => {
  ElMessageBox.alert(info, "Exception information", {
    confirmButtonText: "CONFIRM"
  });
};
</script>

<style lang="less" scoped>
.mod-sys__log-error {
  &-view-info {
    width: 80%;
  }
}
</style>

<template>
  <div class="mod-oss__oss">
    <el-form :inline="true" :model="state.dataForm">
      <el-form-item>
        <el-button type="primary" @click="configHandle()">云存储配置</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="uploadHandle()">上传文件</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="danger" @click="state.deleteHandle()">DELETE</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="state.dataListLoading" :data="state.dataList" border @selection-change="state.dataListSelectionChangeHandle" @sort-change="state.dataListSortChangeHandle" style="width: 100%">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="url" label="URL address" header-align="center" align="center"></el-table-column>
      <el-table-column prop="createDate" label="CREATETIME" sortable="custom" header-align="center" align="center" width="180"></el-table-column>
      <el-table-column label="PROCESS" fixed="right" header-align="center" align="center" width="150">
        <template v-slot="scope">
          <el-button type="primary" link @click="state.deleteHandle(scope.row.id)">DELETE</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :current-page="state.page" :page-sizes="[10, 20, 50, 100]" :page-size="state.limit" :total="state.total" layout="total, sizes, prev, pager, next, jumper" @size-change="state.pageSizeChangeHandle" @current-change="state.pageCurrentChangeHandle"> </el-pagination>
    <!-- 弹窗,Cloud storage configuration -->
    <config v-if="state.configVisible" ref="configRef"></config>
    <!-- 弹窗,Upload file -->
    <upload v-if="state.uploadVisible" ref="uploadRef" @refreshDataList="state.getDataList"></upload>
  </div>
</template>

<script lang="ts" setup>
import useView from "@/hooks/useView";
import { nextTick, reactive, ref, toRefs } from "vue";
import Config from "./oss-config.vue";
import Upload from "./oss-upload.vue";

const configRef = ref();
const uploadRef = ref();

const view = reactive({
  getDataListURL: "/sys/oss/page",
  getDataListIsPage: true,
  deleteURL: "/sys/oss",
  deleteIsBatch: true,
  dataForm: {},
  configVisible: false,
  uploadVisible: false
});

const state = reactive({ ...useView(view), ...toRefs(view) });

//Cloud storage configuration
const configHandle = () => {
  state.configVisible = true;
  nextTick(() => {
    configRef.value.init();
  });
};

//Upload file
const uploadHandle = () => {
  state.uploadVisible = true;
  nextTick(() => {
    uploadRef.value.init();
  });
};
</script>

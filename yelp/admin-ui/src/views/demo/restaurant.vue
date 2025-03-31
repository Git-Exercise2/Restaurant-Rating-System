<template>
  <div class="mod-demo__restaurant">
    <el-form :inline="true" :model="state.dataForm" @keyup.enter="state.getDataList()">
      <el-form-item label="ID">
        <el-input v-model="state.dataForm.id" placeholder="ID" clearable></el-input>
      </el-form-item>
      <el-form-item label="NAME">
        <el-input v-model="state.dataForm.name" placeholder="NAME" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="state.getDataList()" type="primary">SEARCH</el-button>
      </el-form-item>
      <el-form-item>
        <el-button v-if="state.hasPermission('demo:restaurant:delete')" type="danger" @click="state.deleteHandle()">DELETE</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="state.dataListLoading" :data="state.dataList" border @selection-change="state.dataListSelectionChangeHandle" style="width: 100%" height="700">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="id" label="ID" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="name" label="NAME" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="address" label="ADDRESS" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="latitude" label="LATITUDE" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="longitude" label="LONGITUDE" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="rating" label="RATING" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="reviewCount" label="REVIEW COUNT" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="priceLevel" label="PRICE LEVEL" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="categories" label="CATEGORIES" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="imageUrl" label="IMAGE URL" header-align="center" align="center" width="150">
        <template v-slot="scope">
          <el-link :href="scope.row.imageUrl" target="_blank" rel="noopener noreferrer" type="warning">image link</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="url" label="LINK" header-align="center" align="center" width="150">
        <template v-slot="scope">
          <el-link :href="scope.row.url" target="_blank" rel="noopener noreferrer" type="success">restaurant link</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="PHONE" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="createDate" label="CREATE DATE" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column label="PROCESS" fixed="right" header-align="center" align="center" width="200">
        <template v-slot="scope">
          <el-button v-if="state.hasPermission('demo:restaurant:update')" type="success" link @click="addOrUpdateHandle(scope.row.id)">EDIT</el-button>
          <el-button v-if="state.hasPermission('demo:restaurant:delete')" type="danger" link @click="state.deleteHandle(scope.row.id)">DELETE</el-button>
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
import AddOrUpdate from "./restaurant-add-or-update.vue";

const name = ref("")
const attributes = ref("")

const view = reactive({
  deleteIsBatch: true,
  getDataListURL: "/demo/restaurant/page",
  getDataListIsPage: true,
  exportURL: "/demo/restaurant/export",
  deleteURL: "/demo/restaurant",
  dataForm: {
    name: "",
    id: "",
  }
});

const state = reactive({ ...useView(view), ...toRefs(view) });

const addOrUpdateRef = ref();
const addOrUpdateHandle = (id?: number) => {
  addOrUpdateRef.value.init(id);
};
</script>

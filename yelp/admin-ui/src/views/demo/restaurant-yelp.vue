<template>
  <div class="mod-demo__restaurant">
    <el-form :inline="true" :model="state.dataForm" @keyup.enter="getData">
      <el-form-item label="LOCATION">
        <el-input v-model="location" placeholder="LOCATION" clearable></el-input>
      </el-form-item>
      <el-form-item label="ATTRIBUTES">
        <el-input v-model="attributes" placeholder="ATTRIBUTES" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getData" type="primary">SEARCH</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="state.dataListLoading" :data="state.dataList" border @selection-change="state.dataListSelectionChangeHandle" style="width: 100%" height="700">
      <el-table-column type="index" label="INDEX" header-align="center" align="center" width="80" fixed></el-table-column>
      <el-table-column prop="name" label="NAME" header-align="center" align="center" width="200" fixed></el-table-column>
      <el-table-column prop="address" label="ADDRESS" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="latitude" label="LATITUDE" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="longitude" label="LONGITUDE" header-align="center" align="center" width="200"></el-table-column>
      <el-table-column prop="rating" label="RATING" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="reviewCount" label="REVIEW COUNT" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="priceLevel" label="PRICE LEVEL" header-align="center" align="center" width="150"></el-table-column>
      <el-table-column prop="categories" label="CATEGORIES" header-align="center" align="center" width="200"></el-table-column>
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
      <el-table-column label="PROCESS" fixed="right" header-align="center" align="center" width="200">
        <template v-slot="scope">
          <el-button type="danger" link @click="saveToLocale(scope.row)">save</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts" setup>
import useView from "@/hooks/useView";
import { reactive, ref, toRefs } from "vue";
import axios from "axios";
import baseService from "@/service/baseService";
import { ElMessage } from "element-plus";

const view = reactive({
  saveToLocale: "/demo/restaurant/save",
});

const location = ref("")
const attributes = ref("")

interface RestaurantEntity {
  id: number;
  name: string;
  address: string;
  latitude: string;
  longitude: string;
  rating: string;
  reviewCount: number;
  priceLevel: string;
  categories: string;
  imageUrl: string;
  url: string;
  phone: string;
  createDate: string;
  attr1?: string;
  attr2?: string;
  attr3?: string;
  attr4?: string;
}

const state = reactive({ ...useView(view), ...toRefs(view) });

const getData = () => {
  state.dataListLoading = true
  baseService.get(`/demo/restaurant/queryYelpApi?location=` + location.value + "&attributes=" + attributes.value).then((res) => {
    state.dataList = res.data;
  }).finally(() => {
    state.dataListLoading = false
  })
};

const saveToLocale = (row: RestaurantEntity) => {
  state.dataListLoading = true
  baseService.post(`/demo/restaurant/saveYelp`, row).then((res) => {
    ElMessage.success({
      message: 'SUCCESS',
      duration: 500
    });
  }).finally(() => {
    state.dataListLoading = false
  })
};

</script>

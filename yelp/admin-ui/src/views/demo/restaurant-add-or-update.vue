<template>
  <el-dialog v-model="visible" :title="!dataForm.id ? 'ADD' : 'EDIT'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" :rules="rules" ref="dataFormRef" @keyup.enter="dataFormSubmitHandle()" label-width="120px">
          <el-form-item label="RESTAURANT NAME" prop="name">
        <el-input v-model="dataForm.name" placeholder="RESTAURANT NAME"></el-input>
      </el-form-item>
          <el-form-item label="RESTAURANT ADDRESS" prop="address">
        <el-input v-model="dataForm.address" placeholder="RESTAURANT ADDRESS"></el-input>
      </el-form-item>
          <el-form-item label="LATITUDE" prop="latitude">
        <el-input v-model="dataForm.latitude" placeholder="LATITUDE"></el-input>
      </el-form-item>
          <el-form-item label="LONGITUDE" prop="longitude">
        <el-input v-model="dataForm.longitude" placeholder="LONGITUDE"></el-input>
      </el-form-item>
          <el-form-item label="RATING" prop="rating">
        <el-input v-model="dataForm.rating" placeholder="RATING"></el-input>
      </el-form-item>
          <el-form-item label="REVIEW COUNT" prop="reviewCount">
        <el-input v-model="dataForm.reviewCount" placeholder="REVIEW COUNT"></el-input>
      </el-form-item>
          <el-form-item label="PRICE LEVEL" prop="priceLevel">
        <el-input v-model="dataForm.priceLevel" placeholder="PRICE LEVEL"></el-input>
      </el-form-item>
          <el-form-item label="CATEGORIES" prop="categories">
        <el-input v-model="dataForm.categories" placeholder="CATEGORIES"></el-input>
      </el-form-item>
          <el-form-item label="IMAGE URL" prop="imageUrl">
        <el-input v-model="dataForm.imageUrl" placeholder="IMAGE URL"></el-input>
      </el-form-item>
          <el-form-item label="RESTAURANT LINK" prop="url">
        <el-input v-model="dataForm.url" placeholder="RESTAURANT LINK"></el-input>
      </el-form-item>
          <el-form-item label="RESTAURANT PHONE" prop="phone">
        <el-input v-model="dataForm.phone" placeholder="RESTAURANT PHONE"></el-input>
      </el-form-item>
            <el-form-item label="" prop="attr1">
        <el-input v-model="dataForm.attr1" placeholder=""></el-input>
      </el-form-item>
          <el-form-item label="" prop="attr2">
        <el-input v-model="dataForm.attr2" placeholder=""></el-input>
      </el-form-item>
          <el-form-item label="" prop="attr3">
        <el-input v-model="dataForm.attr3" placeholder=""></el-input>
      </el-form-item>
          <el-form-item label="" prop="attr4">
        <el-input v-model="dataForm.attr4" placeholder=""></el-input>
      </el-form-item>
      </el-form>
    <template #footer>
      <el-button @click="visible = false">CANCEL</el-button>
      <el-button type="primary" @click="dataFormSubmitHandle()">CONFIRM</el-button>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { reactive, ref } from "vue";
import baseService from "@/service/baseService";
import { ElMessage } from "element-plus";
const emit = defineEmits(["refreshDataList"]);

const visible = ref(false);
const dataFormRef = ref();

const dataForm = reactive({
  id: '',  name: '',  address: '',  latitude: '',  longitude: '',  rating: '',  reviewCount: '',  priceLevel: '',  categories: '',  imageUrl: '',  url: '',  phone: '',  createDate: '',  attr1: '',  attr2: '',  attr3: '',  attr4: ''});

const rules = ref({
          name: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          address: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          latitude: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          longitude: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          rating: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          reviewCount: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          priceLevel: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          categories: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          imageUrl: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          url: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          phone: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
            attr1: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          attr2: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          attr3: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          attr4: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ]
  });

const init = (id?: number) => {
  visible.value = true;
  dataForm.id = "";

  //Reset form data
  if (dataFormRef.value) {
    dataFormRef.value.resetFields();
  }

  if (id) {
    getInfo(id);
  }
};

//Get information
const getInfo = (id: number) => {
  baseService.get("/demo/restaurant/" + id).then((res) => {
    Object.assign(dataForm, res.data);
  });
};

//Form Submission
const dataFormSubmitHandle = () => {
  dataFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return false;
    }
    (!dataForm.id ? baseService.post : baseService.put)("/demo/restaurant", dataForm).then((res) => {
      ElMessage.success({
        message: 'SUCCESS',
        duration: 500,
        onClose: () => {
          visible.value = false;
          emit("refreshDataList");
        }
      });
    });
  });
};

defineExpose({
  init
});
</script>

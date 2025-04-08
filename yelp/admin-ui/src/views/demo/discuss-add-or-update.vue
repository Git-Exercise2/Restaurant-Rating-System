<template>
  <el-dialog v-model="visible" :title="!dataForm.id ? 'ADD' : 'EDIT'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" :rules="rules" ref="dataFormRef" @keyup.enter="dataFormSubmitHandle()" label-width="120px">
          <el-form-item label="USER ID" prop="userId">
        <el-input v-model="dataForm.userId" placeholder="USER ID"></el-input>
      </el-form-item>
          <el-form-item label="USER NAME" prop="userName">
        <el-input v-model="dataForm.userName" placeholder="USER NAME"></el-input>
      </el-form-item>
          <el-form-item label="PARENT ID" prop="parentId">
        <el-input v-model="dataForm.parentId" placeholder="PARENT ID"></el-input>
      </el-form-item>
          <el-form-item label="PARENT USER ID" prop="parentUserId">
        <el-input v-model="dataForm.parentUserId" placeholder="PARENT USER ID"></el-input>
      </el-form-item>
          <el-form-item label="PARENT USER NAME" prop="parentUserName">
        <el-input v-model="dataForm.parentUserName" placeholder="PARENT USER NAME"></el-input>
      </el-form-item>
          <el-form-item label="RESTAURANT ID" prop="restaurantId">
        <el-input v-model="dataForm.restaurantId" placeholder="RESTAURANT ID"></el-input>
      </el-form-item>
          <el-form-item label="RESTAURANT NAME" prop="restaurantName">
        <el-input v-model="dataForm.restaurantName" placeholder="RESTAURANT NAME"></el-input>
      </el-form-item>
          <el-form-item label="DISCUSS CONTENT" prop="content">
        <el-input v-model="dataForm.content" placeholder="DISCUSS CONTENT"></el-input>
      </el-form-item>
            <el-form-item label="STATUS" prop="isDelete">
        <el-input v-model="dataForm.isDelete" placeholder="STATUS"></el-input>
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
          <el-form-item label="" prop="attr5">
        <el-input v-model="dataForm.attr5" placeholder=""></el-input>
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
  id: '',  userId: '',  userName: '',  parentId: '',  parentUserId: '',  parentUserName: '',  restaurantId: '',  restaurantName: '',  content: '',  createDate: '',  isDelete: '',  attr1: '',  attr2: '',  attr3: '',  attr4: '',  attr5: ''});

const rules = ref({
          userId: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          userName: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          parentId: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          parentUserId: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          parentUserName: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          restaurantId: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          restaurantName: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
          content: [
      { required: true, message: 'The required fields cannot be blank', trigger: 'blur' }
    ],
            isDelete: [
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
    ],
          attr5: [
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
  baseService.get("/demo/discuss/" + id).then((res) => {
    Object.assign(dataForm, res.data);
  });
};

//Form Submission
const dataFormSubmitHandle = () => {
  dataFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return false;
    }
    (!dataForm.id ? baseService.post : baseService.put)("/demo/discuss", dataForm).then((res) => {
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

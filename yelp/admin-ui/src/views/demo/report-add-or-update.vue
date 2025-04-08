<template>
  <el-dialog v-model="visible" :title="!dataForm.id ? 'ADD' : 'EDIT'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" :rules="rules" ref="dataFormRef" @keyup.enter="dataFormSubmitHandle()" label-width="200">
      <!--          <el-form-item label="DISCUSS ID" prop="discussId">-->
      <!--        <el-input v-model="dataForm.discussId" placeholder="DISCUSS ID" disabled></el-input>-->
      <!--      </el-form-item>-->
      <!--          <el-form-item label="Informer id" prop="reportUserId">-->
      <!--        <el-input v-model="dataForm.reportUserId" placeholder="REPORT USER ID" disabled></el-input>-->
      <!--      </el-form-item>-->
<!--      <el-form-item label="Informer" prop="reportUserName">-->
<!--        <el-input v-model="dataForm.reportUserName" placeholder="REPORT USER" disabled></el-input>-->
<!--      </el-form-item>-->
      <!--          <el-form-item label="REPORT TYPE" prop="reportType">-->
      <!--        <el-input v-model="dataForm.reportType" placeholder="REPORT TYPE"></el-input>-->
      <!--      </el-form-item>-->
<!--      <el-form-item label="Report content" prop="reportContent">-->
<!--        <el-input v-model="dataForm.reportContent" placeholder="REPORT CONTENT" disabled></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="informant" prop="attr2">-->
<!--        <el-input v-model="dataForm.attr2" placeholder=""></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="Reported Content" prop="attr1">-->
<!--        <el-input v-model="dataForm.attr1" placeholder=""></el-input>-->
<!--      </el-form-item>-->
      <el-form-item label="Process Result Type" prop="reportHandleType">
        <el-select v-model="dataForm.reportHandleType" @change="">
          <el-option value="1" label="delete comment"></el-option>
          <el-option value="2" label="delete comment and ban user"></el-option>
          <el-option value="3" label="take no measures"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Audit Status" prop="audit">
<!--        <el-input v-model="dataForm.audit" placeholder="AUDIT STATUS"></el-input>-->
        <el-select v-model="dataForm.audit">
          <el-option value="1" label="Pass"></el-option>
          <el-option value="0" label="Fail"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Audit Content" prop="auditContent">
        <el-input v-model="dataForm.auditContent" placeholder="AUDIT CONTENT"></el-input>
      </el-form-item>

<!--      <el-form-item label="" prop="attr3">-->
<!--        <el-input v-model="dataForm.attr3" placeholder=""></el-input>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="" prop="attr4">-->
<!--        <el-input v-model="dataForm.attr4" placeholder=""></el-input>-->
<!--      </el-form-item>-->
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
  id: '',  discussId: '',  reportUserId: '',  reportUserName: '',  reportType: '',  reportContent: '',  reportHandleType: '',  reportHandle: '',  audit: '',  auditContent: '',  createDate: '',  updateDate: '',  attr1: '',  attr2: '',  attr3: '',  attr4: ''});

const rules = ref({
          discussId: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          reportUserId: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          reportUserName: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          reportType: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          reportContent: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          reportHandleType: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          reportHandle: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          audit: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          auditContent: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
              attr1: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          attr2: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          attr3: [
      { required: true, message: 'Empty', trigger: 'blur' }
    ],
          attr4: [
      { required: true, message: 'Empty', trigger: 'blur' }
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
  baseService.get("/demo/report/" + id).then((res) => {
    Object.assign(dataForm, res.data);
  });
};

//Form Submission
const dataFormSubmitHandle = () => {
  dataFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return false;
    }
    if(dataForm.reportHandleType === '1'){
      dataForm.reportHandle = 'delete comment'
    }
    if(dataForm.reportHandleType === '2'){
      dataForm.reportHandle = 'delete comment and ban user'
    }
    if(dataForm.reportHandleType === '3'){
      dataForm.reportHandle = 'take no measures'
    }
    (!dataForm.id ? baseService.post : baseService.put)("/demo/report", dataForm).then((res) => {
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

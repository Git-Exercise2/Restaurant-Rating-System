<template>
  <el-dialog v-model="visible" title="Cloud Storage Configuration" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" :rules="rules" ref="dataFormRef" @keyup.enter="dataFormSubmitHandle()" label-width="140px">
      <el-form-item label="type">
        <el-radio-group v-model="dataForm.type">
          <el-radio :label="1">七牛</el-radio>
          <el-radio :label="2">阿里云</el-radio>
          <el-radio :label="3">腾讯云</el-radio>
        </el-radio-group>
      </el-form-item>
      <template v-if="dataForm.type === 1">
        <el-form-item prop="qiniuDomain" label="domain name">
          <el-input v-model="dataForm.qiniuDomain" placeholder="QiuNiuBined Domain Name"></el-input>
        </el-form-item>
        <el-form-item prop="qiniuPrefix" label="path prefix">
          <el-input v-model="dataForm.qiniuPrefix" placeholder="Do not set default to empty"></el-input>
        </el-form-item>
        <el-form-item prop="qiniuAccessKey" label="AccessKey">
          <el-input v-model="dataForm.qiniuAccessKey" placeholder="QiniuAccessKey"></el-input>
        </el-form-item>
        <el-form-item prop="qiniuSecretKey" label="SecretKey">
          <el-input v-model="dataForm.qiniuSecretKey" placeholder="QiniuSecretKey"></el-input>
        </el-form-item>
        <el-form-item prop="qiniuBucketName" label="Space name">
          <el-input v-model="dataForm.qiniuBucketName" placeholder="Qiniu Storage Space Name"></el-input>
        </el-form-item>
      </template>
      <template v-else-if="dataForm.type === 2">
        <el-form-item prop="aliyunDomain" label="Domain name">
          <el-input v-model="dataForm.aliyunDomain" placeholder="Aliyun-bound domain name, such as: http://cdn.renren.io"></el-input>
        </el-form-item>
        <el-form-item prop="aliyunPrefix" label="path prefix">
          <el-input v-model="dataForm.aliyunPrefix" placeholder="Do not set default to empty"></el-input>
        </el-form-item>
        <el-form-item prop="aliyunEndPoint" label="EndPoint">
          <el-input v-model="dataForm.aliyunEndPoint" placeholder="AliyunEndPoint"></el-input>
        </el-form-item>
        <el-form-item prop="aliyunAccessKeyId" label="AccessKeyId">
          <el-input v-model="dataForm.aliyunAccessKeyId" placeholder="Aliyun AccessKeyId"></el-input>
        </el-form-item>
        <el-form-item prop="aliyunAccessKeySecret" label="AccessKeySecret">
          <el-input v-model="dataForm.aliyunAccessKeySecret" placeholder="Aliyun AccessKeySecret"></el-input>
        </el-form-item>
        <el-form-item prop="aliyunBucketName" label="BucketName">
          <el-input v-model="dataForm.aliyunBucketName" placeholder="AliyunBucketName"></el-input>
        </el-form-item>
      </template>
      <template v-else-if="dataForm.type === 3">
        <el-form-item prop="qcloudDomain" label="domain name">
          <el-input v-model="dataForm.qcloudDomain" placeholder="Tencent Cloud-bound domain name"></el-input>
        </el-form-item>
        <el-form-item prop="qcloudPrefix" label="path prefix">
          <el-input v-model="dataForm.qcloudPrefix" placeholder="Do not set default to empty"></el-input>
        </el-form-item>
        <el-form-item prop="qcloudAppId" label="AppId">
          <el-input v-model="dataForm.qcloudAppId" placeholder="Tencent Cloud AppId"></el-input>
        </el-form-item>
        <el-form-item prop="qcloudSecretId" label="SecretId">
          <el-input v-model="dataForm.qcloudSecretId" placeholder="Tencent Cloud SecretId"></el-input>
        </el-form-item>
        <el-form-item prop="qcloudSecretKey" label="SecretKey">
          <el-input v-model="dataForm.qcloudSecretKey" placeholder="Tencent Cloud SecretKey"></el-input>
        </el-form-item>
        <el-form-item prop="qcloudBucketName" label="BucketName">
          <el-input v-model="dataForm.qcloudBucketName" placeholder="Tencent Cloud BucketName"></el-input>
        </el-form-item>
        <el-form-item prop="qcloudRegion" label="region">
          <el-select v-model="dataForm.qcloudRegion" clearable placeholder="Please select" class="w-percent-100">
            <el-option value="ap-beijing-1" label="Beijing District 1 (North China)"></el-option>
            <el-option value="ap-beijing" label="Beijing"></el-option>
            <el-option value="ap-shanghai" label="Shanghai (East China)"></el-option>
            <el-option value="ap-guangzhou" label="Guangzhou (South China)"></el-option>
            <el-option value="ap-chengdu" label="Chengdu (Southwest)"></el-option>
            <el-option value="ap-chongqing" label="Chongqing"></el-option>
            <el-option value="ap-singapore" label="Singapore"></el-option>
            <el-option value="ap-hongkong" label="Hong Kong"></el-option>
            <el-option value="na-toronto" label="Toronto"></el-option>
            <el-option value="eu-frankfurt" label="Frankfurt"></el-option>
          </el-select>
        </el-form-item>
      </template>
    </el-form>
    <template v-slot:footer>
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
  type: 0,
  qiniuDomain: "",
  qiniuPrefix: "",
  qiniuAccessKey: "",
  qiniuSecretKey: "",
  qiniuBucketName: "",
  aliyunDomain: "",
  aliyunPrefix: "",
  aliyunEndPoint: "",
  aliyunAccessKeyId: "",
  aliyunAccessKeySecret: "",
  aliyunBucketName: "",
  qcloudDomain: "",
  qcloudPrefix: "",
  qcloudAppId: 0,
  qcloudSecretId: "",
  qcloudSecretKey: "",
  qcloudBucketName: "",
  qcloudRegion: ""
});

const rules = ref({
  qiniuDomain: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  qiniuAccessKey: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  qiniuSecretKey: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  qiniuBucketName: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  aliyunDomain: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  aliyunEndPoint: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  aliyunAccessKeyId: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  aliyunAccessKeySecret: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  aliyunBucketName: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  qcloudDomain: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  qcloudAppId: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  qcloudSecretId: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  qcloudSecretKey: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  qcloudBucketName: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  qcloudRegion: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }]
});

const init = () => {
  visible.value = true;

  //Reset form data
  if (dataFormRef.value) {
    dataFormRef.value.resetFields();
  }

  getInfo();
};

//Get information
const getInfo = () => {
  baseService.get("/sys/oss/info").then((res) => {
    Object.assign(dataForm, res.data);
  });
};

//Form Submission
const dataFormSubmitHandle = () => {
  dataFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return false;
    }
    baseService.post("/sys/oss", dataForm).then((res) => {
      ElMessage.success({
        message: "SUCCESS",
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

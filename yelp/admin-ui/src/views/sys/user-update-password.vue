<template>
  <el-form :model="dataForm" :rules="rules" ref="dataFormRef" @keyup.enter="dataFormSubmitHandle()" label-width="120px">
    <el-form-item label="account">
      <span>{{ user.username }}</span>
    </el-form-item>
    <el-form-item prop="password" label="original password">
      <el-input v-model="dataForm.password" type="password" placeholder="original password"></el-input>
    </el-form-item>
    <el-form-item prop="newPassword" label="New Password">
      <el-input v-model="dataForm.newPassword" type="password" placeholder="New Password"></el-input>
    </el-form-item>
    <el-form-item prop="confirmPassword" label="Confirm Password">
      <el-input v-model="dataForm.confirmPassword" type="password" placeholder="Confirm Password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="dataFormSubmitHandle">CONFIRM</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { computed, reactive, ref } from "vue";
import { IObject } from "@/types/interface";
import baseService from "@/service/baseService";
import { useAppStore } from "@/store";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
const router = useRouter();

const dataFormRef = ref();
const dataForm = reactive({
  password: "",
  newPassword: "",
  confirmPassword: ""
});

const store = useAppStore();
const user = computed(() => store.state.user);

const validateConfirmPassword = (rule: IObject, value: string, callback: (e?: Error) => any) => {
  if (dataForm.newPassword !== value) {
    return callback(new Error("Confirm that the password is inconsistent with the new password input"));
  }
  callback();
};

const rules = ref({
  password: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  newPassword: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  confirmPassword: [
    { required: true, message: "The required fields cannot be blank", trigger: "blur" },
    { validator: validateConfirmPassword, trigger: "blur" }
  ]
});

//Form Submission
const dataFormSubmitHandle = () => {
  dataFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return false;
    }
    baseService.put("/sys/user/password", dataForm).then((res) => {
      ElMessage.success({
        message: "SUCCESS",
        duration: 500,
        onClose: () => {
          router.replace("/login");
        }
      });
    });
  });
};
</script>

<template>
  <el-dialog v-model="visible" :title="!dataForm.id ? 'ADD' : 'EDIT'" :close-on-click-modal="false" :close-on-press-escape="false">
    <el-form :model="dataForm" :rules="rules" ref="dataFormRef" @keyup.enter="dataFormSubmitHandle()" label-width="120px">
      <el-form-item prop="dictValue" label="Dictionary value">
        <el-input v-model="dataForm.dictValue" placeholder="Dictionary value"></el-input>
      </el-form-item>
      <el-form-item prop="dictLabel" label="Dictionary tag">
        <el-input v-model="dataForm.dictLabel" placeholder="Dictionary tag"></el-input>
      </el-form-item>
      <el-form-item prop="sort" label="Sort">
        <el-input-number v-model="dataForm.sort" controls-position="right" :min="0" label="Sort"></el-input-number>
      </el-form-item>
      <el-form-item prop="remark" label="Note">
        <el-input v-model="dataForm.remark" placeholder="Note"></el-input>
      </el-form-item>
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
  id: "",
  dictTypeId: "",
  dictLabel: "",
  dictValue: "",
  sort: 0,
  remark: ""
});

const rules = ref({
  dictLabel: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  dictValue: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }],
  sort: [{ required: true, message: "The required fields cannot be blank", trigger: "blur" }]
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
  baseService.get(`/sys/dict/data/${id}`).then((res) => {
    Object.assign(dataForm, res.data);
  });
};

//Form Submission
const dataFormSubmitHandle = () => {
  dataFormRef.value.validate((valid: boolean) => {
    if (!valid) {
      return false;
    }
    (!dataForm.id ? baseService.post : baseService.put)("/sys/dict/data", dataForm).then((res) => {
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
  init,
  dataForm
});
</script>

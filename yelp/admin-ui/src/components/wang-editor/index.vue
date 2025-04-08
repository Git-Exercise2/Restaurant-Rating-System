<template>
  <div style="border: 1px solid #ccc; z-index: 100">
    <!-- 工具栏 -->
    <Toolbar :editor="editorRef" :mode="mode" style="border-bottom: 1px solid #ccc" />
    <!--Editor -->
    <Editor :model-value="modelValue" :style="style" :disabled="disabled" :default-config="editorConfig" :mode="mode" @onCreated="handleCreated" @onChange="handleChange" />
  </div>
</template>

<script lang="ts" setup>
import "@wangeditor/editor/dist/css/style.css";
import { onBeforeUnmount, shallowRef } from "vue";
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { IDomEditor, IEditorConfig } from "@wangeditor/editor";
import app from "@/constants/app";
import { getToken } from "@/utils/cache";

const props = defineProps({
  modelValue: {
    type: String,
    required: true
  },
  mode: {
    type: String,
    default: "default" //Optional value：[default | simple]
  },
  placeholder: {
    type: String,
    default: ""
  },
  style: {
    type: String,
    default: "height: 300px;"
  },
  disabled: {
    type: Boolean,
    default: false
  }
});

//Editor instance，必须用 shallowRef
const editorRef = shallowRef();

type InsertFnType = (url: string, alt: string, href: string) => void;

//Editor configuration
const editorConfig: Partial<IEditorConfig> = {
  placeholder: props.placeholder,
  readOnly: props.disabled,
  MENU_CONF: {
    uploadImage: {
      server: `${app.api}/sys/oss/upload?token=${getToken()}`, //Upload address
      fieldName: "file",
      //Customize insert pictures
      customInsert(res: any, insertFn: InsertFnType) {
        //res is the return result of the server
        //from res 中找到 url alt href ，然后插图图片
        insertFn(res.data.src, "", "");
      }
    }
  }
};

//When component is destroyed，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value;
  if (editor == null) {
    return;
  }
  editor.destroy();
});

const handleCreated = (editor: IDomEditor) => {
  editorRef.value = editor;
};

//Editorchange事件触发
const emit = defineEmits(["update:modelValue"]);
const handleChange = (editor: IDomEditor) => {
  emit("update:modelValue", editor.getHtml());
};
</script>

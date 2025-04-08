<template>
  <svg aria-hidden="true" :class="`iconfont ${className}`" :style="`width:${width};height:${height};color:${color};${style}`">
    <use :xlink:href="symbolId" />
  </svg>
</template>
<script lang="ts">
import { computed, defineComponent } from "vue";

/**
 * Customize the svg icon, you can download the svg icon and store it in the /src/assets/icons/svg directory
 * `How to use: <svg-icon name="earth" color="red"></svg-icon>`
 */
export default defineComponent({
  name: "SvgIcon",
  props: {
    prefix: {
      type: String,
      default: "icon"
    },
    name: {
      type: String,
      required: true
    },
    color: {
      type: String,
      default: ""
    },
    width: String,
    height: String,
    className: { type: String, default: "" },
    style: { type: String, default: "" }
  },
  setup(props) {
    const symbolId = computed(() => `#${props.prefix}-${props.name.replace("icon-", "")}`);
    return { symbolId };
  }
});
</script>

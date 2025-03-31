<template>
  <div class="mod-sys__menu">
<!--    <el-form :inline="true" :model="state.dataForm" @keyup.enter="state.getDataList()">-->
<!--      <el-form-item>-->
<!--        <el-button v-if="state.hasPermission('sys:menu:save')" type="primary" @click="addOrUpdateHandle()">ADD</el-button>-->
<!--      </el-form-item>-->
<!--    </el-form>-->
    <el-table v-loading="state.dataListLoading" :data="state.dataList" show-overflow-tooltip row-key="id" border style="width: 100%">
      <el-table-column prop="name" label="NAME" header-align="center" min-width="150"></el-table-column>
      <el-table-column prop="icon" label="ICON" header-align="center" align="center">
        <template v-slot="scope">
          <svg class="iconfont" aria-hidden="true">
            <use :xlink:href="`#${scope.row.icon}`"></use>
          </svg>
        </template>
      </el-table-column>
      <el-table-column prop="menuType" label="MENU TYPE" header-align="center" align="center">
        <template v-slot="scope">
          <el-tag v-if="scope.row.menuType === 0">MENU</el-tag>
          <el-tag v-else type="info">BUTTON</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="openStyle" label="OPEN STYLE" header-align="center" align="center">
        <template v-slot="scope">
          <span v-if="scope.row.menuType !== 0"></span>
          <el-tag v-else-if="scope.row.openStyle === 1">OPEN OUTER</el-tag>
          <el-tag v-else type="info">OPEN INNER</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="sort" label="SORT" header-align="center" align="center"></el-table-column>
<!--      <el-table-column prop="url" label="ROUTER" header-align="center" align="center" width="150"></el-table-column>-->
<!--      <el-table-column prop="permissions" label="PERMISSION FLAG" header-align="center" align="center" width="200"></el-table-column>-->
<!--      <el-table-column label="PROCESS" fixed="right" header-align="center" align="center" width="200">-->
<!--        <template v-slot="scope">-->
<!--          <el-button v-if="state.hasPermission('sys:menu:save') && scope.row.menuType === 0" type="primary" link @click="addHandle(scope.row)">ADD</el-button>-->
<!--          <el-button v-if="state.hasPermission('sys:menu:update')" type="primary" link @click="addOrUpdateHandle(scope.row.id)">EDIT</el-button>-->
<!--          <el-button v-if="state.hasPermission('sys:menu:delete')" type="primary" link @click="state.deleteHandle(scope.row.id)">DELETE</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>
    <!-- 弹窗, ADD / EDIT -->
    <add-or-update ref="addOrUpdateRef" @refreshDataList="state.getDataList"></add-or-update>
  </div>
</template>

<script lang="ts" setup>
import useView from "@/hooks/useView";
import { reactive, ref, toRefs } from "vue";
import AddOrUpdate from "./menu-add-or-update.vue";

const view = reactive({
  getDataListURL: "/sys/menu/list",
  deleteURL: "/sys/menu"
});

const state = reactive({ ...useView(view), ...toRefs(view) });

const addOrUpdateRef = ref();
const addOrUpdateHandle = (id?: number) => {
  addOrUpdateRef.value.init(id);
};

const addHandle = (row: any) => {
  addOrUpdateRef.value.init2(row);
};
</script>

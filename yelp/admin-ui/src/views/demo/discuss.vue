<template>
  <div class="mod-demo__discuss">
    <el-form :inline="true" :model="state.dataForm" @keyup.enter="state.getDataList()">
      <!--      <el-form-item>-->
      <!--        <el-button v-if="state.hasPermission('demo:discuss:save')" type="primary" @click="addOrUpdateHandle()">ADD</el-button>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item>-->
      <!--        <el-button v-if="state.hasPermission('demo:discuss:delete')" type="danger" @click="state.deleteHandle()">DELETE</el-button>-->
      <!--      </el-form-item>-->
    </el-form>
    <el-table v-loading="state.dataListLoading" :data="state.dataList" border
              @selection-change="state.dataListSelectionChangeHandle" style="width: 100%">
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="id" label="ID" header-align="center" align="center" width="200"></el-table-column>
      <!--              <el-table-column prop="userId" label="USER ID" header-align="center" align="center" width="150"></el-table-column>-->
      <el-table-column prop="userName" label="COMMENT USER" header-align="center" align="center"
                       width="150"></el-table-column>
      <!--              <el-table-column prop="parentId" label="PARENT ID" header-align="center" align="center"></el-table-column>-->
      <!--              <el-table-column prop="parentUserId" label="PARENT USER ID" header-align="center" align="center" width="200"></el-table-column>-->
      <el-table-column prop="parentUserName" label="REPLY TO" header-align="center" align="center"
                       width="200"></el-table-column>
      <!--              <el-table-column prop="restaurantId" label="RESTAURANT ID" header-align="center" align="center" width="200"></el-table-column>-->
      <el-table-column prop="restaurantName" label="RESTAURANT NAME" header-align="center" align="center"
                       width="200"></el-table-column>
      <el-table-column prop="content" label="CONTENT" header-align="center" align="center"
                       width="200"></el-table-column>
      <el-table-column prop="createDate" label="CREATE DATE" header-align="center" align="center"
                       width="200"></el-table-column>
      <el-table-column prop="isDelete" label="STATUS" header-align="center" align="center" width="200">
        <template v-slot="scope">
          {{ scope.row.isDelete === "1" ? "DELETED" : "NORMAL" }}
        </template>
      </el-table-column>
      <el-table-column label="PROCESS" fixed="right" header-align="center" align="center" width="250">
        <template v-slot="scope">
          <el-button @click="reportComment(scope.row)"  type="warning" >report</el-button>
          <el-button v-if="state.hasPermission('demo:discuss:delete')" type="danger"
                     @click="state.deleteHandle(scope.row.id)">delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination :current-page="state.page" :page-sizes="[10, 20, 50, 100]" :page-size="state.limit"
                   :total="state.total" layout="total, sizes, prev, pager, next, jumper"
                   @size-change="state.pageSizeChangeHandle"
                   @current-change="state.pageCurrentChangeHandle"></el-pagination>
    <add-or-update ref="addOrUpdateRef" @refreshDataList="state.getDataList">CONFIRM</add-or-update>
  </div>

  <!-- 对话框主体 -->
  <el-dialog
    v-model="state.dataForm.dialogVisible"
    title="Report Dialog"
    width="30%"
    :before-close="handleClose"
    @opened="handleOpened">
    <el-input ref="inputRef" v-model="state.dataForm.inputValue" placeholder="Please input report content" clearable />
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="state.dataForm.dialogVisible = false">cancel</el-button>
        <el-button type="primary" @click="handleReport">report</el-button>
      </span>
    </template>

  </el-dialog>

</template>

<script lang="ts" setup>
import useView from "@/hooks/useView";
import { computed, reactive, ref, toRefs } from "vue";
import AddOrUpdate from "./discuss-add-or-update.vue";
import { useAppStore } from "@/store";
import baseService from "@/service/baseService";
import { ElMessage, ElMessageBox } from "element-plus";
import { IObject } from "@/types/interface";

const store = useAppStore();

const user = computed(() => store.state.user);

const view = reactive({
  deleteIsBatch: true,
  getDataListURL: "/demo/discuss/page",
  getDataListIsPage: true,
  exportURL: "/demo/discuss/export",
  deleteURL: "/demo/discuss",
  dataForm: {
    dialogVisible: false,
    inputValue: '',
    discussEntity: ''
  }
});

const state = reactive({ ...useView(view), ...toRefs(view) });

const addOrUpdateRef = ref();
const addOrUpdateHandle = (id?: number) => {
  addOrUpdateRef.value.init(id);
};

export interface DiscussEntity {
  id: number;
  userId: number;
  userName: string;
  parentId: number;
  parentUserId: number;
  parentUserName: string;
  restaurantId: number;
  restaurantName: string;
  content: string;
  createDate: string;
  isDelete: string;
  attr1: string;
  attr2: string;
  attr3: string;
  attr4: string;
  attr5: string;
}

export interface ReportDTO {
  id: number;
  discussId: number;
  reportUserId: number;
  reportUserName: string;
  reportType: string;
  reportContent: string;
  reportHandleType: string;
  reportHandle: string;
  audit: string;
  auditContent: string;
  createDate: string;
  updateDate: string;
  attr1: string;
  attr2: string;
  attr3: string;
  attr4: string;
}

const reportComment = (discussEntity: DiscussEntity) => {
  state.dataForm.dialogVisible = true
  state.dataForm.discussEntity = discussEntity
}

const handleReport = () => {
  if (state.dataForm.inputValue === null || state.dataForm.inputValue === ''){
    ElMessage.error({
      message: "Please fill in the input field",
      duration: 500
    });
    return
  }

  let row = state.dataForm.discussEntity

  const reportDto: ReportDTO = {
    discussId: row.id,
    reportUserId: store.state.user.id,
    reportUserName: store.state.user.username,
    reportContent: state.dataForm.inputValue,
    attr1: row.content,
    attr2: row.userName
  };

  state.dataListLoading = true;
  baseService.post(`/demo/report`, reportDto).then((res) => {
    ElMessage.success({
      message: "Report Success",
      duration: 500
    });
    state.dataForm.inputValue = false
  }).finally(() => {
    state.dataListLoading = false;
    state.dataForm.dialogVisible = false
  });
};


</script>

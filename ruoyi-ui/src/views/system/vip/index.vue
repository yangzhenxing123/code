<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="电话" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:vip:add']"
          >新增</el-button
        >
      </el-col>
      <!--<el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:vip:edit']"
          >修改</el-button
        >
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:vip:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:vip:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="vipList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
              label="操作"
              align="center"
              class-name="small-padding fixed-width"
            >
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['system:vip:edit']"
                  >修改</el-button
                >
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['system:vip:remove']"
                  >删除</el-button
                >
              </template>
            </el-table-column>
      <el-table-column label="电话" align="center" prop="phone" />
      <el-table-column label="余额" align="center" prop="money">
        <template slot-scope="scope">
          <span
            style="color: red; cursor: pointer"
            @click="getMoneyList(scope.row)"
            >{{ scope.row.money }}</span
          >
        </template>
      </el-table-column>
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="创建人" align="center" prop="createUserName" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
      </el-table-column>

    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改vip对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="350px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="余额" prop="money">
          <el-input v-model="form.money" placeholder="请输入余额" />
        </el-form-item>
        <el-form-item label="创建人" prop="createUserName">
          <el-input v-model="form.createUserName" placeholder="请输入创建人" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 消费记录对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="openMoney"
      width="380px"
      append-to-body
    >
      <el-table v-loading="loading" :data="moneyList">
        <el-table-column label="姓名" align="center" prop="name" />
        <el-table-column label="余额" align="center" prop="money" />
        <el-table-column
          label="创建时间"
          align="center"
          prop="createTime"
          width="180"
        >
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import {
  listVip,
  getVip,
  delVip,
  addVip,
  updateVip,
  exportVip,
  moneyList,
  selectPhone,
} from "@/api/system/vip";

export default {
  name: "Vip",
  components: {},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // vip表格数据
      vipList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //余额数据
      moneyList: [],
      //是否显示余额弹出层
      openMoney: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        phone: null,
        name: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
         phone: [
           //{ required: true, validator: this.isPassportNo, trigger: "blur" },
           { required: true, message: "电话不能为空", trigger: "blur" },
         ],
        name: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
        money: [{ required: true, message: "余额不能为空", trigger: "blur" }],
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /* 手机号码查重 */
    isPassportNo(rule, value, callback) {
      let reg = /^[1][3,4,5,6,7,8,9][0-9]{9}$/;
      if (
        value == "" ||
        value == undefined ||
        value == null ||
        value == "null"
      ) {
        callback(new Error("手机号码不能为空"));
      } else {
        if (!reg.test(value)) {
          callback(new Error("手机号码格式不正确"));
        } else {
          selectPhone({ phone: value }).then((res) => {
            // console.log(res);
            debugger
            if (!res) {
              callback(new Error("手机号码已存在，请重新输入"));
            } else {
              callback();
            }
          });
        }
      }
    },
    /** 查询vip列表 */
    getList() {
      this.loading = true;
      listVip(this.queryParams).then((response) => {
        this.vipList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        phone: null,
        name: null,
        money: null,
        createUserId: null,
        createUserName: null,
        createTime: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加vip";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getVip(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改vip";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateVip(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVip(this.form).then((response) => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      // this.$confirm('是否确认删除vip编号为"' + ids + '"的数据项?', "警告", {
        this.$confirm('是否确认删除vip?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delVip(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm("是否确认导出所有vip数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportVip(queryParams);
        })
        .then((response) => {
          this.download(response.msg);
        });
    },
    /** 余额查询操作 */
    getMoneyList(row) {
      const id = row.id;
      moneyList({ vipId: id }).then((response) => {
        console.log(response);
        this.moneyList = response.rows;
        this.openMoney = true;
        this.title = "消费记录";
      });
    },
  },
};
</script>
<style>
  .el-message-box{
    width:350px;
  }
</style>

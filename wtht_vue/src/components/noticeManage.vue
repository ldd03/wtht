<template>
  <div class="notice-manage-wrapper">
    <div class="notice-manage-card">
      <!-- 顶部筛选区 -->
      <div class="notice-manage-header">
        <span class="filter-label">条件筛选</span>
        <el-select v-model="selectInfo.status" placeholder="请选择公告状态" class="filter-select">
          <el-option label="正常" value="正常"></el-option>
          <el-option label="已结束" value="已结束"></el-option>
          <el-option label="全部" value="全部"></el-option>
        </el-select>
        <el-button type="primary" plain @click="onSelectSubmit" class="filter-btn">筛选</el-button>
        <el-button type="success" plain class="add-btn" @click="onAddClick">新增公告</el-button>
      </div>

      <!-- 表格数据 -->
      <el-table :data="tableData" height="auto" border style="width: 100%" class="notice-table">
        <el-table-column prop="title" label="标题" align="center"></el-table-column>
        <el-table-column prop="content" label="内容" align="center">
          <template #default="scope">
            <el-tooltip
              class="item"
              effect="light"
              placement="top"
            >
              <template #content>
                <div class="tooltip-content">{{ scope.row.content }}</div>
              </template>
              <span class="content-ellipsis">{{ scope.row.content }}</span>
            </el-tooltip>
          </template>
        </el-table-column>
        <el-table-column prop="publishTime" label="创建时间" align="center"></el-table-column>
        <el-table-column prop="outTime" label="有效截止时间" align="center"></el-table-column>
        <el-table-column prop="status" label="公告状态" align="center">
          <template #default="scope">
            <span
              :class="{
                'status-normal': scope.row.status === '正常',
                'status-ended': scope.row.status === '已结束'
              }"
              class="status-badge"
            >
              {{ scope.row.status }}
            </span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="160">
          <template #default="scope">
            <div class="action-btn-group">
              <el-button size="default" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button size="default" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </div>
          </template>
        </el-table-column>
      </el-table>

      <!-- 编辑对话框 -->
      <el-dialog title="公告信息编辑框" v-model="dialogVisibleEdit" width="30%">
        <el-form ref="form" :model="notice" label-width="80px">
          <el-form-item label="标题">
            <el-input v-model="notice.title"></el-input>
          </el-form-item>
          <el-form-item label="截止时间">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="notice.outTime" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="状态">
            <el-switch v-model="notice.status" active-value="正常" inactive-value="已结束"></el-switch>
          </el-form-item>
          <el-form-item label="内容">
            <el-input type="textarea" v-model="notice.content"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onEditSubmit">保存</el-button>
            <el-button @click="dialogVisibleEdit = false">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>

      <!-- 新增对话框 -->
      <el-dialog title="新增公告" v-model="dialogVisibleAdd" width="30%">
        <el-form ref="form" :model="notice" label-width="80px">
          <el-form-item label="标题">
            <el-input v-model="notice.title"></el-input>
          </el-form-item>
          <el-form-item label="截止时间">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="notice.outTime" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="状态">
            <el-switch v-model="notice.status" active-value="正常" inactive-value="已结束"></el-switch>
          </el-form-item>
          <el-form-item label="内容">
            <el-input type="textarea" v-model="notice.content"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="addHandle">提交</el-button>
            <el-button @click="dialogVisibleAdd = false">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'NoticeManage',
  data() {
    return {
      tableData: [],
      notice: {
        noticeId: -1,
        title: "",
        content: "",
        publishTime: "",
        outTime: "",
        status: "",
        context: ""
      },
      selectInfo: {
        status: ""
      },
      dialogVisibleAdd: false,
      dialogVisibleEdit: false
    }
  },
  methods: {
    // 获取数据
    getTableData() {
      const url = "/notices";
      axios.get(url).then((resp) => {
        if (resp.data.code == 20001) {
          this.tableData = resp.data.data;
        }
      })
    },
    // 编辑
    handleEdit(index, row) {
      const url = "/notices/byId/" + row.noticeId;
      axios.get(url).then((resp) => {
        this.notice = resp.data.data;
        this.dialogVisibleEdit = true;
      })
    },
    onEditSubmit() {
      const url = "/notices"
      axios.put(url, this.notice).then((resp) => {
        if (resp.data.code == 20011) {
          this.$message.success('公告信息修改成功');
          this.getTableData();
        } else {
          this.$message.error('公告信息修改失败');
        }
      })
      this.dialogVisibleEdit = false;
    },
    // 删除
    handleDelete(index, row) {
      const url = "/notices/" + row.noticeId;
      this.$confirm('确认删除该公告信息？不建议删除', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete(url).then((resp) => {
          if (resp.data.code == 20021) {
            this.$message.success('公告删除成功!');
            this.getTableData();
          } else {
            this.$message.error('公告删除失败!');
          }
        })
      }).catch(() => {
        this.$message.info('已取消删除');
      });
    },
    // 筛选
    onSelectSubmit() {
      if (!this.selectInfo.status || this.selectInfo.status === "全部") {
        this.getTableData();
        return;
      }
      const url = "/notices/byStatus/" + this.selectInfo.status;
      axios.get(url).then((resp) => {
        if (resp.data.code == 20001) {
          this.tableData = resp.data.data;
          if (!this.tableData.length) {
            this.$message.warning('未找到符合条件的公告');
          } else {
            this.$message.success('公告筛选成功');
          }
        } else {
          this.$message.error('公告筛选失败');
        }
      })
    },
    // 新增
    addHandle() {
      const url = "/notices";
      axios.post(url, this.notice).then((resp) => {
        if (resp.data.code == 20051) {
          this.$message.success('新增公告成功');
          this.getTableData();
        } else {
          this.$message.error('新增公告失败');
        }
      }).finally(() => {
        this.dialogVisibleAdd = false;
        this.getTableData();
      })
    },
    resetNotice() {
      this.notice = {
        noticeId: -1,
        title: "",
        content: "",
        publishTime: "",
        outTime: "",
        status: ""
      };
    },
    onAddClick() {
      this.resetNotice();
      this.dialogVisibleAdd = true;
    }
  },
  mounted() {
    this.getTableData();
  }
}
</script>

<style scoped>
.notice-manage-wrapper {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  height: 97%;
  background: #f5f7fa;
  padding: 12px 8px 8px 8px;
}
.notice-manage-card {
  background: #fff;
  border-radius: 14px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.08);
  padding: 10px 10px 10px 10px;
  width: 100%;
  height: 97%;
  display: flex;
  flex-direction: column;
}
.notice-manage-header {
  display: flex;
  align-items: center;
  gap: 16px;
  width: 100%;
  margin-bottom: 24px;
}
.filter-label {
  font-size: 18px;
  color: #666;
  margin-right: 8px;
  white-space: nowrap;
}
.filter-select {
  width: 160px;
}
.filter-btn,
.add-btn {
  height: 32px;
  line-height: 32px;
  font-size: 14px;
  padding: 0 14px;
}
.add-btn {
  margin-left: 0;
}
.notice-table {
  border-radius: 10px;
  overflow: hidden;
  font-size: 15px;
}
.no-wrap-form {
  flex-wrap: nowrap !important;
}
.action-btn-group {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 8px;
  white-space: nowrap;
}
.status-badge {
  display: inline-block;
  padding: 4px 18px;
  border-radius: 12px;
  color: #fff;
  font-weight: bold;
  font-size: 14px;
  letter-spacing: 2px;
  box-shadow: 0 2px 8px rgba(66,201,155,0.06);
  transition: background 0.2s;
}
.status-normal {
  background: linear-gradient(90deg, #42c99b 0%, #4fd7b0 100%);
}
.status-ended {
  background: linear-gradient(90deg, #bfcbd9 0%, #909399 100%);
}
.table-btn {
  height: 34px;
  font-size: 15px;
  padding: 0 16px;
}
/* 隐藏 el-table 的竖直滚动条 */
::v-deep .el-table__body-wrapper {
  overflow-y: hidden !important;
  scrollbar-width: none; /* Firefox */
  -ms-overflow-style: none; /* IE 10+ */
}
::v-deep .el-table__body-wrapper::-webkit-scrollbar {
  display: none; /* Chrome/Safari/Webkit */
}
.content-ellipsis {
  display: inline-block;
  max-width: 260px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  vertical-align: middle;
  cursor: pointer;
}
/* 自定义 el-tooltip 浅色气泡提示背景色 */
::v-deep .el-tooltip__popper.is-light {
  background: #a7c6ed !important;
  color: #333 !important;
  border: 2px solid #1e2936 !important;
  box-shadow: 0 2px 8px rgba(66,201,155,0.06);
  font-size: 22px !important;
  line-height: 1.7 !important;
  padding: 12px 18px !important;
}

/* 设置悬浮时显示内容的交互效果 */
.tooltip-content {
  font-size: 18px;
  line-height: 1.7;
  color: #1a1919;
  word-break: break-all;
  max-width: 300px;
}
</style>
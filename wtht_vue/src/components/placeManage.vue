<template>
  <div class="place-manage-wrapper">
    <div class="place-manage-card">
      <!-- 顶部筛选区 -->
      <div class="place-manage-header">
        <span class="filter-label">场地类别</span>
        <el-select v-model="selectInfo.type" placeholder="请选择场地类别" class="filter-select">
          <el-option label="室内乒乓球" value="室内乒乓球场"></el-option>
          <el-option label="室内羽毛球" value="室内羽毛球场"></el-option>
          <el-option label="室外乒乓球" value="室外乒乓球场"></el-option>
          <el-option label="室外篮球" value="室外篮球场"></el-option>
          <el-option label="全部" value="全部"></el-option>
        </el-select>
        <el-button type="primary" plain @click="onSelectSubmit" class="filter-btn">筛选</el-button>
        <el-button type="success" plain @click="dialogVisibleAdd = true" class="add-btn">新增场地</el-button>
      </div>

      <!-- 表格数据 -->
      <el-table :data="tableData" height="auto" border style="width: 100%" class="place-table">
        <el-table-column prop="name" label="场地类别" align="center"></el-table-column>
        <el-table-column prop="site" label="地点" align="center"></el-table-column>
        <el-table-column prop="facility" label="体育设施" align="center"></el-table-column>
        <el-table-column prop="description" label="场地描述" align="center"></el-table-column>
        <el-table-column prop="status" label="场地状态" align="center">
          <template #default="scope">
            <span
              :class="{
                'status-badge': true,
                'status-normal': scope.row.status === '可用',
                'status-ended': scope.row.status === '维护中'
              }"
            >
              {{ scope.row.status }}
            </span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="160">
          <template #default="scope">
            <el-button size="default" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="default" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 编辑对话框 -->
      <el-dialog title="场地信息编辑框" v-model="dialogVisibleEdit" width="30%">
        <el-form :model="place" label-width="80px">
          <el-form-item label="场地类别">
            <el-input v-model="place.name"></el-input>
          </el-form-item>
          <el-form-item label="地点">
            <el-input v-model="place.site"></el-input>
          </el-form-item>
          <el-form-item label="体育设施">
            <el-input v-model="place.facility"></el-input>
          </el-form-item>
          <el-form-item label="是否可用">
            <el-switch v-model="place.status" active-value="可用" inactive-value="维护中"></el-switch>
          </el-form-item>
          <el-form-item label="场地描述">
            <el-input type="textarea" v-model="place.description"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onEditSubmit">保存</el-button>
            <el-button @click="dialogVisibleEdit = false">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>

      <!-- 新增对话框 -->
      <el-dialog title="新增场地" v-model="dialogVisibleAdd" width="30%">
        <el-form :model="place" label-width="80px">
          <el-form-item label="场地类别">
            <el-input v-model="place.name"></el-input>
          </el-form-item>
          <el-form-item label="地点">
            <el-input v-model="place.site"></el-input>
          </el-form-item>
          <el-form-item label="体育设施">
            <el-input v-model="place.facility"></el-input>
          </el-form-item>
          <el-form-item label="是否可用">
            <el-switch v-model="place.status" active-value="可用" inactive-value="维护中"></el-switch>
          </el-form-item>
          <el-form-item label="场地描述">
            <el-input type="textarea" v-model="place.description"></el-input>
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
  name: 'PlaceManage',
  data() {
    return {
      tableData: [],
      place: {
        placeId: -1,
        name: "",
        site: "",
        facility: "",
        description: "",
        status: ""
      },
      selectInfo: {
        type: ""
      },
      dialogVisibleAdd: false,
      dialogVisibleEdit: false
    }
  },
  methods: {
    getTableData() {
      axios.get('/places').then((resp) => {
        if (resp.data.code == 20001) {
          this.tableData = resp.data.data;
        }
      })
    },
    handleEdit(index, row) {
      axios.get('/places/byId/' + row.placeId).then((resp) => {
        this.place = resp.data.data;
        this.dialogVisibleEdit = true;
      })
    },
    onEditSubmit() {
      axios.put('/places', this.place).then((resp) => {
        if (resp.data.code == 20011) {
          this.$message.success('场地信息修改成功');
          this.getTableData();
        } else {
          this.$message.error('场地信息修改失败');
        }
      }).catch(() => {
        this.$message.error('网络错误，场地信息修改失败');
      });
      this.dialogVisibleEdit = false;
    },
    handleDelete(index, row) {
      this.$confirm('确认删除该场地信息？不建议删除', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete('/places/' + row.placeId).then((resp) => {
          if (resp.data.code == 20021) {
            this.$message.success('场地信息删除成功!');
            this.getTableData();
          } else {
            this.$message.error('场地信息删除失败!');
          }
        })
      }).catch(() => {
        this.$message.info('已取消删除');
      });
    },
    onSelectSubmit() {
      if (!this.selectInfo.type || this.selectInfo.type === '全部') {
        this.getTableData();
      } else {
        axios.get('/places/byType/' + this.selectInfo.type).then((resp) => {
          if (resp.data.code == 20001) {
            this.tableData = resp.data.data;
            if (!this.tableData.length) {
              this.$message.warning('未找到符合条件的场地');
            } else {
              this.$message.success('场地筛选成功');
            }
          } else {
            this.$message.error('场地筛选失败');
          }
        })
      }
    },
    addHandle() {
      axios.post('/places', this.place).then((resp) => {
        if (resp.data.code == 20051) {
          this.$message.success('新增场地成功');
          this.getTableData();
        } else {
          this.$message.error('新增场地失败');
        }
      }).finally(() => {
        this.dialogVisibleAdd = false;
        this.getTableData();
      })
    }
  },
  mounted() {
    this.getTableData();
  }
}
</script>

<style scoped>
.place-manage-wrapper {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  height: 97%;
  background: #f5f7fa;
  padding: 12px 8px 8px 8px;
}
.place-manage-card {
  background: #fff;
  border-radius: 14px;
  box-shadow: 0 4px 20px rgba(0,0,0,0.08);
  padding: 10px 10px 10px 10px;
  width: 100%;
  height: 97%;
  display: flex;
  flex-direction: column;
}
.place-manage-header {
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
.place-table {
  border-radius: 10px;
  overflow: hidden;
  font-size: 15px;
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
</style>
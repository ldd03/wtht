<template>
  <div class="users-manage">
    <!-- 筛选区 -->
    <el-form :inline="true" :model="filter" class="filter-form">
      <el-input v-model="filter.idSearch" placeholder="班级/学号/工号" style="width: 200px;" clearable />
      <el-button type="primary" @click="onSelectSubmit" class="filter-btn">筛选</el-button>
      <el-button @click="resetFilter">重置</el-button>
      <el-button type="success" plain @click="onAddClick" :disabled="!canAddUser()" class="add-btn">新增用户</el-button>
    </el-form>

    <!-- 用户表格 -->
    <el-table :data="tableData" border height="600" style="width: 100%; margin-top: 20px;">
      <el-table-column prop="userId" label="学号/工号" align="center" />
      <el-table-column prop="name" label="姓名" align="center" />
      <el-table-column prop="password" label="密码" align="center">
        <template #default="scope">
          <span>{{ scope.row.password ? '******' : '' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="role" label="角色" align="center" />
      <el-table-column prop="status" label="账号状态" align="center">
        <template #default="scope">
          <el-tag
            :type="scope.row.status === '正常' ? 'success' : 'danger'"
            effect="plain"
            round
          >
            {{ scope.row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="180">
        <template #default="scope">
          <el-button 
            size="mini" 
            @click="handleEdit(scope.row)" 
            :disabled="!canEditUser(scope.row)"
          >
            编辑
          </el-button>
          <el-button 
            size="mini" 
            type="danger" 
            @click="handleDelete(scope.row)" 
            :disabled="!canDeleteUser(scope.row)"
          >
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 编辑用户弹窗 -->
    <el-dialog title="用户信息编辑" v-model="dialogVisibleEdit" width="400px">
      <el-form :model="user" label-width="80px">
        <el-form-item label="学号">
          <el-input v-model="user.userId" disabled />
        </el-form-item>
        <el-form-item label="姓名">
          <el-input 
            v-model="user.name" 
            :disabled="!canEditUser(user) || isSelfAndNotAdmin(user)" 
          />
        </el-form-item>
        <el-form-item label="密码">
          <el-input 
            v-model="user.password" 
            type="password" 
            :disabled="!canEditUser(user) && !isSelfAndNotAdmin(user)" 
          />
        </el-form-item>
        <el-form-item label="是否可用">
          <el-switch 
            v-model="user.status" 
            active-value="正常" 
            inactive-value="禁用" 
            :disabled="!canEditUser(user) || isEditingSelf(user) || isSelfAndNotAdmin(user)" 
          />
        </el-form-item>
        <el-form-item label="角色">
          <el-select 
            v-model="user.role" 
            placeholder="请选择账号角色" 
            :disabled="!canEditUser(user) || isEditingSelf(user) || isSelfAndNotAdmin(user)"
          >
            <el-option label="学生" value="学生" />
            <el-option label="教师" value="教师" />
            <el-option label="管理员" value="管理员" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button 
            type="primary" 
            @click="onEditSubmit" 
            :disabled="!canEditUser(user)"
          >
            保存
          </el-button>
          <el-button @click="dialogVisibleEdit = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 新增用户弹窗 -->
    <el-dialog title="新增用户" v-model="dialogVisibleAdd" width="400px">
      <el-form :model="user" label-width="80px">
        <el-form-item label="学号">
          <el-input v-model="user.userId" />
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="user.name" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="user.password" type="password" />
        </el-form-item>
        <el-form-item label="是否可用">
          <el-switch v-model="user.status" active-value="正常" inactive-value="禁用" />
        </el-form-item>
        <el-form-item label="角色">
          <el-select v-model="user.role" placeholder="请选择账号角色">
            <el-option label="学生" value="学生" />
            <el-option label="教师" value="教师" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addHandle">提交</el-button>
          <el-button @click="dialogVisibleAdd = false">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'UsersManage',
  data() {
    return {
      filter: {
        idSearch: ''
      },
      tableData: [],
      user: {
        userId: '',
        name: '',
        password: '',
        role: '',
        status: '正常'
      },
      dialogVisibleAdd: false,
      dialogVisibleEdit: false
    }
  },
  methods: {
    // 判断当前用户是否可以编辑指定用户
    canEditUser(targetUser) {
      const currentUserId = localStorage.getItem('userId');
      const currentUserRole = localStorage.getItem('userRole');
      
      
      // 管理员可以编辑所有用户
      if (currentUserRole === '管理员') {
        return true;
      }
      
      // 教师和学生只能编辑自己的信息
      if (currentUserRole === '教师' || currentUserRole === '学生') {
        const canEdit = targetUser.userId === currentUserId;
        return canEdit;
      }
      
      console.log('无权限编辑');
      return false;
    },
    
    // 判断当前用户是否可以删除指定用户
    canDeleteUser(targetUser) {
      const currentUserRole = localStorage.getItem('userRole');
      
      // 管理员可以删除所有非管理员用户
      if (currentUserRole === '管理员') {
        return targetUser.role !== '管理员';
      }
      
      // 教师和学生不能删除任何用户
      if (currentUserRole === '教师' || currentUserRole === '学生') {
        return false;
      }
      
      return false;
    },
    
    // 判断当前用户是否可以新增用户
    canAddUser() {
      const currentUserRole = localStorage.getItem('userRole');
      
      // 只有管理员可以新增用户
      return currentUserRole === '管理员';
    },
    
    isEditingSelf(targetUser) {
      const currentUserId = localStorage.getItem('userId');
      const currentUserRole = localStorage.getItem('userRole');
      // 只有管理员编辑自己时，才禁用
      return currentUserRole === '管理员' && targetUser.userId === currentUserId;
    },
    
    isSelfAndNotAdmin(targetUser) {
      const currentUserId = localStorage.getItem('userId');
      const currentUserRole = localStorage.getItem('userRole');
      return (currentUserRole !== '管理员') && (targetUser.userId === currentUserId);
    },
    
    getTableData() {
      axios.get('/users').then(resp => {
        if (resp.data.code === 20001) {
          this.tableData = resp.data.data
        }
      })
    },
    handleEdit(row) {
      // 权限验证
      if (!this.canEditUser(row)) {
        this.$message.error('您没有权限编辑该用户信息');
        return;
      }
      
      axios.get(`/users/byId/${row.userId}`).then(resp => {
        this.user = resp.data.data
        this.dialogVisibleEdit = true
      })
    },
    onEditSubmit() {
      // 权限验证
      if (!this.canEditUser(this.user)) {
        this.$message.error('您没有权限编辑该用户信息');
        return;
      }
      
      axios.put('/users', this.user).then(resp => {
        if (resp.data.code === 20011) {
          this.$message.success('用户信息修改成功');
          this.getTableData();
        } else {
          this.$message.error('用户信息修改失败');
        }
      }).catch(() => {
        this.$message.error('网络错误，用户信息修改失败');
      });
      this.dialogVisibleEdit = false;
    },
    handleDelete(row) {
      // 权限验证
      if (!this.canDeleteUser(row)) {
        this.$message.error('您没有权限删除该用户');
        return;
      }
      
      this.$confirm('确认删除该用户信息？不建议删除', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete(`/users/${row.userId}`).then(resp => {
          if (resp.data.code === 20021) {
            this.$message.success('用户删除成功!');
            this.getTableData();
          } else {
            this.$message.error('用户删除失败!');
          }
        })
      }).catch(() => {
        this.$message.info('已取消删除');
      })
    },
    onSelectSubmit() {
      if (this.filter.idSearch && this.filter.idSearch !== '全部') {
        axios.get(`/users/byUserId/${this.filter.idSearch}`).then(resp => {
          if (resp.data.code === 20001) {
            this.tableData = resp.data.data
            if (!this.tableData.length) {
              this.$message.warning('未找到符合条件的用户');
            } else {
              this.$message.success('用户筛选成功');
            }
          } else {
            this.$message.error('用户筛选失败');
          }
        })
      } else {
        this.getTableData()
      }
    },
    onAddClick() {
      // 权限验证
      if (!this.canAddUser()) {
        this.$message.error('您没有权限新增用户');
        return;
      }
      
      this.user = {
        userId: '',
        name: '',
        password: '',
        role: '',
        status: '正常'
      };
      this.dialogVisibleAdd = true;
    },
    addHandle() {
      // 权限验证
      if (!this.canAddUser()) {
        this.$message.error('您没有权限新增用户');
        return;
      }
      
      axios.post('/users', this.user).then(resp => {
        if (resp.data.code === 20051) {
          this.$message.success('新增用户成功');
          this.getTableData();
        } else {
          this.$message.error('新增用户失败');
        }
      })
      this.dialogVisibleAdd = false
    },
    resetFilter() {
      this.filter.idSearch = ''
      this.getTableData()
    }
  },
  mounted() {
    this.getTableData()
  }
}
</script>

<style scoped>
.users-manage {
  padding: 24px;
}
.filter-form {
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  gap: 10px;
}
</style> 
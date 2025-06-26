<template>
  <div class="my-info">
    <div class="content-wrapper">
      <h2 class="main-title">我的信息</h2>
      <div class="info-section">
        <!-- 左侧：我的账号信息 -->
        <div class="left-section">
          <div class="top-row">我的账号信息</div>
          <el-table :data="myInfoTable" border class="info-table" :show-header="false">
            <el-table-column prop="label" width="220" align="center">
              <template #default="scope">
                <span class="field-label">{{ scope.row.label }}</span>
              </template>
            </el-table-column>
            <el-table-column prop="value" align="center">
              <template #default="scope">
                <span class="field-value">
                  <template v-if="scope.row.label === '密码'">
                    {{ scope.row.value ? '******' : '' }}
                  </template>
                  <template v-else>
                    {{ scope.row.value }}
                  </template>
                </span>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <!-- 右侧：注意事项 -->
        <div class="right-section">
          <div class="notice-card">
            <h4>注意事项</h4>
            <ul>
              <li>1. 请确认您的账号信息是否正确。</li>
              <li>2. 如果信息有误，请及时联系管理员。</li>
              <li>3. 请勿将账号密码透露给他人。</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'MyInfo',
  data() {
    return {
      myInfo: {
        userId: '',
        name: '',
        password: '',
        role: '',
        status: ''
      },
      myInfoTable: []
    }
  },
  methods: {
    getMyInfo() {
      const userId = localStorage.getItem('userId')
      axios.get(`/myInfo/${userId}`).then(resp => {
        if (resp.data.code === 20001) {
          this.myInfo = resp.data.data
          this.myInfoTable = [
            { label: '账号/学号/工号', value: this.myInfo.userId },
            { label: '用户名', value: this.myInfo.name },
            { label: '密码', value: this.myInfo.password },
            { label: '角色', value: this.myInfo.role },
            { label: '状态', value: this.myInfo.status }
          ]
        } else {
          this.$message.error('获取个人信息失败')
        }
      }).catch(() => {
        this.$message.error('网络请求错误')
      })
    }
  },
  mounted() {
    this.getMyInfo()
  }
}
</script>

<style scoped>
.my-info {
  min-height: 100vh;
  background: #f7f8fa;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
}
.content-wrapper {
  max-width: 1100px;
  width: 100%;
  margin: 56px auto 0 auto;
}
.main-title {
  text-align: center;
  margin-bottom: 48px;
  font-size: 30px;
  font-weight: 800;
  color: #222;
  letter-spacing: 2px;
}
.info-section {
  display: flex;
  gap: 40px;
  justify-content: center;
  align-items: stretch;
}
.left-section, .right-section {
  background: #fff;
  border-radius: 22px;
  box-shadow: 0 8px 32px rgba(0,0,0,0.08);
  padding: 38px 38px 28px 38px;
  min-width: 340px;
  max-width: 480px;
  flex: 1 1 420px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  transition: box-shadow 0.3s, transform 0.3s;
}
.left-section:hover, .right-section:hover {
  box-shadow: 0 12px 32px rgba(0,0,0,0.16);
  transform: translateY(-0.5px) scale(1.03);
  z-index: 2;
}
.left-section {
  min-width: 380px;
  max-width: 520px;
}
.right-section {
  min-width: 260px;
  max-width: 340px;
  align-items: flex-start;
}
.left-section, .right-section {
  min-height: 340px;
}
.top-row {
  text-align: left;
  font-size: 20px;
  padding: 0 0 16px 0;
  background: none;
  border-bottom: 2px solid #e6e6e6;
  margin-bottom: 16px;
  border-radius: 0;
  font-weight: 700;
  color: #409EFF;
}
.info-table {
  border-radius: 10px;
  overflow: hidden;
  font-size: 17px;
  background: #fff;
  box-shadow: none;
}
.info-table .el-table__header th {
  background: #f7faff;
  color: #409EFF;
  font-weight: 700;
  font-size: 17px;
  border-bottom: 2px solid #e6e6e6;
}
.info-table .el-table__body td {
  background: #fff;
  color: #222;
  font-size: 17px;
  border-bottom: 1px solid #f0f0f0;
  padding: 14px 8px;
}
.info-table .el-table__body tr:hover > td {
  background: #f4f8ff;
  transition: background 0.2s;
}
.info-table .el-table__cell {
  border-right: none;
}
.info-table .el-table__row td:first-child {
  color: #409EFF;
  font-weight: 400;
  text-align: center;
  letter-spacing: 1px;
  width: 220px;
}
.info-table .el-table__row td:last-child {
  text-align: center;
}
.field-label {
  font-weight: 450;
  color: #222;
  font-size: 15px;
}
.field-value {
  word-break: break-all;
  color: #444;
  font-size: 15px;
  line-height: 2.1;
}
.notice-card h4 {
  color: #409EFF;
  font-size: 20px;
  font-weight: 800;
  margin-bottom: 18px;
  letter-spacing: 1px;
}
.notice-card ul {
  padding-left: 18px;
  margin: 0;
  color: #222;
  font-size: 16px;
  line-height: 2.1;
}
@media (max-width: 1100px) {
  .info-section {
    flex-direction: column;
    gap: 18px;
    align-items: center;
  }
  .content-wrapper {
    margin: 24px 8px 0 8px;
  }
  .left-section, .right-section {
    max-width: 100%;
    min-width: 0;
    min-height: unset;
  }
}
</style> 
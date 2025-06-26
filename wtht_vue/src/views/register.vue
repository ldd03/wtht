<template>
  <div class="container">
    <div class="header">
      <img src="@/assets/img/logo1.png" alt="华北水利水电大学">
      <span> | </span>
      <span>文体会堂预约系统</span>
    </div>
    <div class="register-bg">
      <div class="register-box">
        <h2>统一身份认证-注册</h2>
        <el-form ref="form" :model="user" label-width="80px">
          <el-form-item label="账号：">
            <el-input v-model="user.userId" placeholder="学号/工号"></el-input>
          </el-form-item>
          <el-form-item label="密码：">
            <el-input placeholder="密码" v-model="user.password" show-password></el-input>
          </el-form-item>
          <el-form-item label="确认密码：">
            <el-input placeholder="确认密码" v-model="user.confirmPassword" show-password></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onRegisterSubmit" class="register-button">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="footer">
      版权所有：华北水利水电大学 | 豫ICP备05002494-1 | 豫公网安备 41010702002321号
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Register',
  data() {
    return {
      user: {
        userId: '',
        password: '',
        confirmPassword: ''
      }
    }
  },
  methods: {
    onRegisterSubmit() {
      if (this.user.password !== this.user.confirmPassword) {
        this.$message.error('两次输入的密码不一致');
        return;
      }
      const url = '/register';
      axios.post(url, this.user).then((resp) => {
        if (resp.data.msg === 'success') {
          this.registerSuccessPromptMsg();
          setTimeout(() => {
            this.$router.push('/login');
          }, 1000);
        } else {
          this.registerErrorMsg();
        }
      })
    },
    registerSuccessPromptMsg() {
      this.$message({
        message: '注册成功！',
        type: 'success'
      })
    },
    registerErrorMsg() {
      this.$message.error('注册失败，请检查输入信息后重试');
    }
  }
}
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}
.header {
  position: absolute;
  top: 20px;
  left: 20px;
  color: #fff;
  font-size: 20px;
  display: flex;
  align-items: center;
}
.header img {
  height: 40px;
  margin-right: 10px;
}
.register-bg {
  padding: 50px;
  border-radius: 20px;
  background: rgba(100, 95, 95, 0.164);
  box-shadow: 0 0 32px rgba(0, 0, 0, 0.22);
  width: 420px;
  height: auto;
  margin-left: 800px;
  backdrop-filter: blur(16px);
  -webkit-backdrop-filter: blur(16px);
  border: 1.5px solid rgba(255,255,255,0.25);
  display: flex;
  justify-content: center;
  align-items: center;
}
.register-box {
  background-color: transparent;
  padding: 40px;
  border-radius: 20px;
  box-shadow: none;
  height: auto;
  width: 320px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.register-box h2 {
  margin-bottom: 20px;
  border-bottom: 2px solid #2196f3;
  display: inline-block;
  color: #fff;
  font-size: 30px;
  text-align: left;
  margin-left: 30px;
  margin-bottom: 30px;
  font-weight: bold;
  letter-spacing: 2px;
}
.el-form-item__label {
  color: #fff;
  font-size: 16px;
  font-weight: 500;
}
.el-input__wrapper {
  background: rgba(255,255,255,0.98) !important;
  border-radius: 8px !important;
  box-shadow: none !important;
  border: none !important;
}
.el-input__inner {
  color: #223355;
  font-size: 16px;
  font-weight: 500;
}
.el-input.is-focus .el-input__wrapper {
  box-shadow: 0 2px 0 0 #2196f3 !important;
  border-bottom: 1.5px solid #2196f3 !important;
}
.el-button {
  width: 100%;
  height: 50px;
  font-size: 20px;
  border-radius: 8px;
}
.register-button {
  width: 100%;
  height: 48px;
  font-size: 20px;
  background-color: #2196f3;
  color: #fff;
  border: none;
  border-radius: 10px;
  transition: background-color 0.3s ease-in-out;
  font-weight: bold;
  letter-spacing: 2px;
  box-shadow: 0 2px 8px rgba(33,150,243,0.08);
}
.register-button:hover {
  background-color: #1976d2;
}
.footer {
  position: absolute;
  bottom: 10px;
  width: 100%;
  text-align: center;
  color: #fff;
  font-size: 12px;
}
</style> 
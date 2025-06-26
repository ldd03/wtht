<template>
  <div class="container">
    <div class="header">
      <img src="@/assets/img/logo1.png" alt="华北水利水电大学" />
      <span> | </span>
      <span>文体会堂预约系统</span>
    </div>
    <div class="login-bg">
      <div class="login-box">
        <h2>统一身份认证平台</h2>
        <el-form :model="user" :rules="rules" ref="loginForm" label-width="80px" @keyup.enter="onSubmit">
          <el-form-item label="账号：" prop="userId">
            <el-input v-model="user.userId" placeholder="学号/工号">
              <template #prefix>
                <el-icon><User /></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item label="密码：" prop="password">
            <el-input v-model="user.password" placeholder="密码" show-password>
              <template #prefix>
                <el-icon><Lock /></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <div class="login-options">
              <el-checkbox v-model="rememberMe">记住我</el-checkbox>
              <span class="forgot-password">
                <a href="/reset-password" style="color: inherit; text-decoration: none;">忘记密码</a>
              </span>
              <span>
                <a class="register-link" @click.prevent="$router.replace('/login')" style="color: inherit; text-decoration: none;">注册</a>
              </span>
            </div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit" class="login-button" :loading="loading">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
    <div class="login-footer">
      
      
    </div>
    <div class="footer">
      版权所有：华北水利水电大学 | 豫ICP备05002494-1 | 豫公网安备 41010702002321号
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import axios from 'axios'
import { User, Lock } from '@element-plus/icons-vue'

const router = useRouter()
const loginForm = ref(null)
const loading = ref(false)
const rememberMe = ref(false)
const user = reactive({
  userId: '',
  password: ''
})

const rules = {
  userId: [
    { required: true, message: '请输入账号', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 5, message: '密码不能少于5位', trigger: 'blur' }
  ]
}

function onSubmit() {
  loginForm.value.validate(valid => {
    if (!valid) return
    loading.value = true
    axios.post('/logins', user).then((resp) => {
      loading.value = false
      if (resp.data.msg === 'success') {
        ElMessage.success('登录成功')
        localStorage.setItem('userId', user.userId)
        const role = resp.data.role || (resp.data.data && resp.data.data.role)
        if (role) {
          localStorage.setItem('userRole', role)
        }
        if (rememberMe.value) {
          localStorage.setItem('rememberMe', '1')
        } else {
          localStorage.removeItem('rememberMe')
        }
        setTimeout(() => {
          localStorage.setItem('currentPage', 'loginIndex')
          router.push('/menu/index')
        }, 1000)
      } else {
        ElMessage.error('账号或密码错误，请检查错误后重试')
      }
    }).catch(() => {
      loading.value = false
    })
  })
}

// 背景图片切换
const backgrounds = [
  new URL('@/assets/img/background1.jpg', import.meta.url).href,
  new URL('@/assets/img/background2.jpg', import.meta.url).href,
  new URL('@/assets/img/background3.jpg', import.meta.url).href,
  new URL('@/assets/img/background4.jpg', import.meta.url).href
]

function changeBackground() {
  const index = Math.floor(Math.random() * backgrounds.length)
  document.body.style.backgroundImage = `url('${backgrounds[index]}')`
  document.body.style.backgroundPosition = 'left'
  document.body.style.backgroundSize = 'cover'
  document.body.style.transition = 'background-position 1s ease-in-out'
  document.body.style.backgroundRepeat = 'no-repeat'
}

setInterval(changeBackground, 3000)
window.addEventListener('load', changeBackground)
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
}
.login-bg {
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
}
.login-box {
  background-color: transparent;
  padding: 40px;
  border-radius: 20px;
  box-shadow: none;
  height: auto;
  width: 320px;
}
.login-box h2 {
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
/* 更改输入框样式，当输入框错误时，显示 */
:deep(.is-error .el-input__wrapper) {
  box-shadow: 0 0 0 1px #2196f3 !important; 
  border-color: #171718 !important;
}
/* 账号和信息 */
:deep(.el-form-item__label) {
  color: #fff !important;
  font-size: 16px;
  font-weight: 500;
}
/* 星号 * */
:deep(.el-form-item__label)::before {
  color: #fff !important;
  font-size: 18px !important;
  font-weight: bold !important;
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
.login-button {
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
.login-button:hover {
  background-color: #1976d2;
}
.login-options {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  margin-bottom: 0;
  margin-top: -10px;
}

:deep(.el-checkbox__label) {
  color: #fff !important;
  font-size: 15px;
  font-weight: 500;
  cursor: pointer;
  text-decoration: none !important;
  transition: none !important;
}

.forgot-password a,
.register-link {
  color: #fff !important;
  font-size: 15px;
  font-weight: 500;
  cursor: pointer;
  text-decoration: none !important;
  transition: none !important;
}

.forgot-password a:hover,
.register-link:hover {
  color: #fff !important;
  text-decoration: none !important;
}
.footer {
  position: absolute;
  bottom: 10px;
  width: 100%;
  text-align: center;
  color: #fff;
  font-size: 12px;
}
.header {
  position: absolute;
  top: 20px;
  left: 20px;
  color: #fff;
  font-size: 20px;
}
.header img {
  height: 40px;
  margin-right: 10px;
}
.login-footer {
  text-align: center;
  margin-top: 18px;
  color: #888;
  font-size: 15px;
}
</style>
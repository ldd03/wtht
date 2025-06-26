<template>
  <div class="main-layout">
    <!-- 顶部标识栏 -->
    <div class="header-bar">
      <img src="@/assets/img/logo1.png" class="header-logo" />
      <span class="header-title">文体会堂预约信息管理系统界面</span>
    </div>
    <!-- 下方区域：左侧菜单+右侧内容 -->
    <div class="body-area">
      <div class="sidebar">
        <div class="sidebar-header">
          <span class="sidebar-title">管理菜单</span>
        </div>
        <div
          v-for="item in menuItems"
          :key="item.name"
          class="sidebar-item"
          :class="{ 'active-module': currentPage === item.name }"
          @click="selectPage(item.name)"
        >
          <span class="sidebar-icon" v-if="item.icon">
            <i :class="item.icon"></i>
          </span>
          {{ item.label }}
        </div>
      </div>
      <div class="main-content">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Menu",
  data() {
    return {
      currentPage: '',
      menuItems: [
        { name: 'loginIndex', label: '主界面', icon: 'el-icon-s-home' },
        { name: 'noticeManage', label: '公告管理', icon: 'el-icon-message' },
        { name: 'placeManage', label: '场地管理', icon: 'el-icon-office-building' },
        { name: 'placeSubscribe', label: '场地预约', icon: 'el-icon-date' },
        { name: 'subscribeHistory', label: '预约历史信息', icon: 'el-icon-document' },
        { name: 'usersManage', label: '用户信息管理', icon: 'el-icon-user' },
        { name: 'myInfo', label: '我的信息', icon: 'el-icon-info' }
      ]
    };
  },
  methods: {
    selectPage(page) {
      this.currentPage = page;
      localStorage.setItem('currentPage', page);
      this.$router.push({ name: page });
    }
  },
  mounted() {
    const savedPage = localStorage.getItem('currentPage');
    if (savedPage) {
      this.currentPage = savedPage;
    } else {
      this.currentPage = 'loginIndex';
    }
  }
}
</script>

<style scoped>
.main-layout {
  height: 100vh;
  display: flex;
  flex-direction: column;
}
.header-bar {
  width: 100%;
  height: 70px;
  background: linear-gradient(90deg, #699ade 0%, #699ade 80%);
  display: flex;
  align-items: center;
  padding: 0 40px;
  box-sizing: border-box;
  color: #fff;
  font-size: 28px;
  font-weight: bold;
  letter-spacing: 2px;
  justify-content: flex-start;
}
.sidebar {
  width: 190px;  /* 调整菜单栏宽度*/
  background: linear-gradient(180deg, #699ade 0%, #699ade 80%);
  color: #fff;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: stretch;
  min-height: 100%;
  box-shadow: 2px 0 8px rgba(0,0,0,0.08);
}
.header-logo {
  height: 50px;
  margin-right: 14px;
}
.header-title {
  font-size: 28px;
  font-weight: bold;
  margin-left: 350px;
}
.body-area {
  flex: 1;
  display: flex;
  min-height: 0;
}
.sidebar-header {
  display: flex;
  align-items: center;
  padding: 24px 20px 16px 20px;
  border-bottom: 1px solid rgba(255,255,255,0.1);
  justify-content: center;
}
.sidebar-title {
  font-size: 20px;
  font-weight: bold;
  letter-spacing: 2px;
}
.sidebar-item {
  display: flex;
  align-items: center;
  padding: 14px 24px;
  margin: 8px 12px;
  border-radius: 8px;
  font-size: 17px;
  cursor: pointer;
  transition: background 0.2s, color 0.2s;
  user-select: none;
}
.sidebar-item .sidebar-icon {
  margin-right: 10px;
  font-size: 18px;
}
.sidebar-item:hover {
  background: rgba(255,255,255,0.18);
  color: #222;
}
.active-module {
  background: #fff;
  color: #3a7bd5;
  font-weight: bold;
  box-shadow: 0 2px 8px rgba(0,0,0,0.08);
}
.main-content {
  flex: 1;
  background: #fff;
  padding: 0;
  min-height: 100%;
  overflow: auto;
}
</style>
<template>
  <div class="main-index">
    <h1 class="main-title">系统操作指南</h1>
    <div class="card-section">
      <!-- 左卡片 -->
      <div class="card left-card">
        <div class="group-title">管理员功能</div>
        <div class="divider"></div>
        <div class="group-list">
          <div><b>公告管理：</b>添加、修改、删除公告，及时发布重要通知。</div>
          <div><b>场地管理：</b>增加、删除、修改场地信息，维护场地状态。</div>
          <div><b>用户管理：</b>查看、增加、删除、修改用户信息，管理用户权限。</div>
          <div><b>预约管理：</b>审批场地和器材预约的信息，确保资源合理分配。</div>
        </div>
        <div class="group-title" style="margin-top: 24px;">个人信息管理</div>
        <div class="divider"></div>
        <div class="group-list">
          <div><b>查看个人信息：</b>登录后自动显示个人基本信息。</div>
          <div><b>修改个人信息：</b>更新联系方式或密码，保持信息最新。</div>
        </div>
      </div>
      <!-- 右卡片 -->
      <div class="card right-card">
        <div class="group-title">用户功能</div>
        <div class="divider"></div>
        <div class="group-list">
          <div><b>场地预约：</b>浏览可用场地，选择合适时间进行预约。</div>
          <div><b>预约管理：</b>查看已预约的信息，及时了解预约状态。</div>
          <div><b>历史记录：</b>查看历史预约的记录，方便追踪使用情况。</div>
        </div>
        <div class="group-title" style="margin-top: 24px;">使用提示</div>
        <div class="divider"></div>
        <div class="group-list">
          <div><b>预约须知：</b>请提前24小时进行预约，确保场地可用。</div>
          <div><b>取消规则：</b>如需取消预约，请提前4小时操作。</div>
          <div><b>使用规范：</b>请遵守场地使用规定，爱护公共设施。</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginIndex'
}
</script>

<style scoped>
.main-index {
  background: #f7f8fa;
  height: "100%";
}
.main-title {
  text-align: center;
  font-size: 32px;
  font-weight: 700;
  height: '95%';
  padding: 36px 0px 0px 0px;
  letter-spacing: 2px;
  color: #222;
}
.card-section {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  gap: 36px;
  max-width: 1200px;
  padding: 36px 0px 310px 150px;
  flex-wrap: wrap;
}
.card {
  background: #fff;
  border-radius: 18px;
  box-shadow: 0 4px 18px rgba(0,0,0,0.09);
  padding: 36px 38px 32px 38px;
  min-width: 340px;
  max-width: 520px;
  flex: 1 1 420px;
  display: flex;
  flex-direction: column;
  margin-bottom: 32px;
  transition: box-shadow 0.3s, transform 0.3s;
}
.card:hover {
  box-shadow: 0 12px 32px rgba(0,0,0,0.16);
  transform: translateY(-0.5px) scale(1.03);
  z-index: 2;
}
.left-card {
  margin-right: 0;
}
.right-card {
  margin-left: 0;
}
.group-title {
  color: #409EFF;
  font-size: 20px;
  font-weight: 700;
  margin-bottom: 8px;
}
.divider {
  height: 2px;
  background: #e6e6e6;
  margin-bottom: 16px;
  border-radius: 2px;
}
.group-list {
  color: #444;
  font-size: 16px;
  line-height: 2.1;
  margin-bottom: 0;
}
.group-list b {
  color: #222;
  font-weight: 600;
}
@media (max-width: 1000px) {
  .card-section {
    flex-direction: column;
    gap: 18px;
    align-items: stretch;
    max-width: 98vw;
  }
  .card {
    max-width: 100%;
    min-width: 0;
    margin-bottom: 18px;
  }
  .main-title {
    font-size: 26px;
  }
}
</style> 
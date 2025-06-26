<template>
  <div class="place-manage-wrapper">
    <div class="place-manage-card">
      <!-- 顶部筛选区 -->
      <div class="place-manage-header">
        <span class="filter-label">职位</span>
        <el-select v-model="filter.role" placeholder="请选择职位" class="filter-select">
          <el-option label="全部" value=""></el-option>
          <el-option label="教师" value="教师"></el-option>
          <el-option label="学生" value="学生"></el-option>
        </el-select>
        <span class="filter-label">场地类别</span>
        <el-select v-model="filter.placeName" placeholder="请选择场地类别" class="filter-select">
          <el-option v-for="item in placeOptions" :key="item" :label="item" :value="item"></el-option>
        </el-select>
        <span class="filter-label">预约用户</span>
        <el-input v-model="filter.userName" placeholder="请输入预约用户姓名" class="filter-select" style="width: 160px;"></el-input>
        <span class="filter-label">日期</span>
        <el-date-picker
          v-model="filter.date"
          type="date"
          placeholder="请选择日期"
          class="filter-select"
          style="width: 160px;"
        ></el-date-picker>
        <span class="filter-label">时间段</span>
        <el-select v-model="filter.timeRange" placeholder="请选择时间段" class="filter-select">
          <el-option v-for="item in timeRangeOptions" :key="item" :label="item" :value="item"></el-option>
        </el-select>
        <el-button type="primary" plain @click="onFilterSubmit" class="filter-btn">筛选</el-button>
        <el-button type="default" plain @click="onFilterReset">重置</el-button>
      </div>
      <!-- 表格数据 -->
      <el-table :data="tableData" height="auto" border style="width: 100%" class="place-table">
        <el-table-column prop="place.name" label="场地类别" align="center"></el-table-column>
        <el-table-column prop="place.site" label="位置" align="center"></el-table-column>
        <el-table-column prop="user.name" label="预约人姓名" align="center"></el-table-column>
        <el-table-column prop="user.role" label="角色" align="center"></el-table-column>
        <el-table-column prop="avaliableTime.timeRange" label="预约时间段" align="center"></el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center"></el-table-column>
        <el-table-column prop="status" label="预约状态" align="center"></el-table-column>
        <el-table-column prop="remark" label="备注" align="center">
          <template #default="scope">
            <el-tooltip
              class="item"
              effect="light"
              placement="top"
            >
              <template #content>
                <div class="tooltip-content">{{ scope.row.remark }}</div>
              </template>
              <span class="content-ellipsis">{{ scope.row.remark }}</span>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'SubscribeHistory',
  data() {
    return {
      tableData: [],
      filter: {
        role: '',
        placeName: '',
        userName: '',
        date: '',
        timeRange: ''
      },
      placeOptions: [],
      timeRangeOptions: []
    }
  },
  methods: {
    getTableData() {
      axios.get('/historys').then((resp) => {
        if (resp.data.code == 20001) {
          this.tableData = resp.data.data;
        }
      })
    },
    getPlaceOptions() {
      axios.get('/places').then(resp => {
        if (resp.data.code == 20001) {
          const names = [...new Set(resp.data.data.map(item => item.name))];
          this.placeOptions = ['全部', ...names];
        }
      });
    },
    getTimeRangeOptions() {
      axios.get('/avaliableTimes').then(resp => {
        if (resp.data.code == 20001) {
          this.timeRangeOptions = resp.data.data.map(item => item.timeRange);
        }
      });
    },
    onFilterSubmit() {
      let date = this.filter.date;
      if (date instanceof Date) {
        // 格式化为 yyyy-MM-dd
        const y = date.getFullYear();
        const m = String(date.getMonth() + 1).padStart(2, '0');
        const d = String(date.getDate()).padStart(2, '0');
        date = `${y}-${m}-${d}`;
      }
      if (typeof date !== 'string' || !/^\d{4}-\d{2}-\d{2}$/.test(date)) {
        date = '';
      }
      const params = {
        role: this.filter.role,
        placeName: this.filter.placeName === '全部' ? '' : this.filter.placeName,
        userName: this.filter.userName,
        date: date,
        timeRange: this.filter.timeRange
      };
      axios.get('/historys/filter', { params }).then(resp => {
        if (resp.data.code == 20001) {
          this.tableData = resp.data.data;
          if (!this.tableData.length) {
            this.$message.warning('未找到符合条件的预约记录');
          } else {
            this.$message.success('预约记录筛选成功');
          }
        } else {
          this.$message.error('预约记录筛选失败');
        }
      });
    },
    onFilterReset() {
      this.filter = { role: '', placeName: '', userName: '', date: '', timeRange: '' };
      this.getTableData();
    }
  },
  mounted() {
    this.getTableData();
    this.getPlaceOptions();
    this.getTimeRangeOptions();
  },
  watch: {
    'filter.date'(val) {
      console.log('当前选择的日期：', val);
    }
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
.content-ellipsis {
  display: inline-block;
  max-width: 260px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  vertical-align: middle;
  cursor: pointer;
}
::v-deep .el-tooltip__popper.is-light {
  background: #a7c6ed !important;
  color: #333 !important;
  border: 2px solid #1e2936 !important;
  box-shadow: 0 2px 8px rgba(66,201,155,0.06);
  font-size: 22px !important;
  line-height: 1.7 !important;
  padding: 12px 18px !important;
}
.tooltip-content {
  font-size: 18px;
  line-height: 1.7;
  color: #1a1919;
  word-break: break-all;
  max-width: 300px;
}
</style>

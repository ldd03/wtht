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
        <el-table-column label="操作" align="center">
          <template #default="scope">
            <el-button
              size="mini"
              @click="handleSubscribe(scope.$index, scope.row)"
              :disabled="scope.row.status !== '可用'"
              class="reserve-btn"
            >预约</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 弹出预约对话框 -->
      <el-dialog title="请选择预约时间" v-model="dialogVisibleAdd" width="30%">
        <el-form ref="form" :model="subscribeData" label-width="80px">
          <el-form-item label="场地类别">
            <el-input v-model="subscribeData.place.name" disabled></el-input>
          </el-form-item>
          <el-form-item label="地点">
            <el-input v-model="subscribeData.place.site" disabled></el-input>
          </el-form-item>
          <el-form-item label="日期">
            <el-col :span="11">
              <el-date-picker type="date" placeholder="选择日期" v-model="subscribeData.dateTime" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="时间段" style="width: 265px;">
            <el-select v-model="subscribeData.timeRange" placeholder="请选择时间段">
              <el-option v-for="item in subscribeData.options" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注">
            <el-input type="textarea" v-model="subscribeData.remark" placeholder="请输入备注信息（可选）" :rows="3"></el-input>
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
  name: 'PlaceSubscribe',
  data() {
    return {
      tableData: [],
      selectInfo: {
        type: ''
      },
      dialogVisibleAdd: false,
      subscribeData: {
        timeId: -1,
        dateTime: '',
        timeRange: '',
        remark: '',
        options: [],
        user: {},
        place: {}
      },
      history: {
        historyId: '',
        user: {},
        place: {},
        avaliableTime: {},
        createTime: '',
        status: '',
        remark: ''
      }
    }
  },
  methods: {
    getTableData() {
      const url = '/placeSubscribes';
      axios.get(url).then((resp) => {
        if (resp.data.code == 20001) {
          this.tableData = resp.data.data;
        }
      })
    },
    handleSubscribe(index, row) {
      this.dialogVisibleAdd = true;
      const url = '/placeSubscribes/byId/' + row.placeId;
      axios.get(url).then((resp) => {
        this.getTimeRange();
        this.subscribeData.user.userId = localStorage.getItem('userId');
        this.subscribeData.place = resp.data.data;
        this.dialogVisibleAdd = true;
      })
    },
    getTimeRange() {
      const url = '/placeSubscribes/getTimeRange';
      axios.get(url).then((resp) => {
        this.subscribeData.options = resp.data.data;
      })
    },
    onSelectSubmit() {
      const url = '/placeSubscribes/byType/' + this.selectInfo.type;
      if (!this.selectInfo.type || this.selectInfo.type === '全部') {
        this.getTableData();
      } else {
        axios.get(url).then((resp) => {
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
      // 数据验证
      if (!this.subscribeData.timeRange) {
        this.$message.error('请选择预约时间段');
        return;
      }
      
      if (!this.subscribeData.dateTime) {
        this.$message.error('请选择预约日期');
        return;
      }
      
      // 构建预约数据
      this.history.place = this.subscribeData.place;
      this.history.user = this.subscribeData.user;
      
      // 根据选择的时间段ID找到对应的AvaliableTime对象
      const selectedTimeRange = this.subscribeData.timeRange;
      const avaliableTime = {
        timeId: selectedTimeRange
      };
      this.history.avaliableTime = avaliableTime;
      
      // 设置备注信息
      this.history.remark = this.subscribeData.remark || "";

      // 调用subscribeHistory控制器中的接口
      const url = '/historys/add';
      axios.post(url, this.history).then((resp) => {
        if (resp.data.code == 20051) {
          this.$message.success('预约成功');
          this.dialogVisibleAdd = false;
          this.getTableData();
          // 清空表单数据
          this.subscribeData.timeRange = "";
          this.subscribeData.dateTime = "";
          this.subscribeData.remark = "";
        } else {
          this.$message.error(resp.data.msg || '预约失败，请重试');
        }
      }).catch((error) => {
        console.error('预约失败:', error);
        this.$message.error('预约失败，请重试');
      });
    },
    addSuccessMsg() {
      this.$message({
        message: '新增成功',
        type: 'success'
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

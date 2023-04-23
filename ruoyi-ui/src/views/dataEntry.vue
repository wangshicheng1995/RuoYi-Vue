<template>
  <div class="app-container">
    <el-row type="flex" justify="space-between" align="middle">
      <el-col :span="11">
        <el-table :data="oldMasterData" v-loading="oldLoading" style="width: 100%">
          <el-table-column prop="edcname" label="EDC" width="150">
          </el-table-column>
          <el-table-column prop="projectName" label="项目" width="100">
          </el-table-column>
          <el-table-column prop="price" label="单价"> </el-table-column>
          <el-table-column prop="taxes" label="税费(Y/N)"> </el-table-column>
          <el-table-column prop="managementFree" label="管理费(Y/N)">
          </el-table-column>
        </el-table>
      </el-col>
      <el-row type="flex" justify="space-between">
        <el-col :span="24">
          <el-button size="medium" :disabled="buttonDisable" @click="getCompareResult">开始对比</el-button>
        </el-col>
      </el-row>
      <el-col :span="11">
        <el-table :data="newMasterData" v-loading="newLoading" style="width: 100%">
          <el-table-column prop="edcname" label="EDC" width="150">
          </el-table-column>
          <el-table-column prop="projectName" label="项目" width="100">
          </el-table-column>
          <el-table-column prop="price" label="单价"> </el-table-column>
          <el-table-column prop="taxes" label="税费(Y/N)"> </el-table-column>
          <el-table-column prop="managementFree" label="管理费(Y/N)">
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <div v-if="showResult" style="margin-top: 50px">
      <div>对比结果</div>
      <ul v-for="item in compareResult" :key="item.id">
        <li v-if="item.flag === 'update'">
          {{ item.projectName }}项目的{{ item.field }}属性修改了，新的值为
          {{ item.new }}。
        </li>
        <li v-if="item.flag === 'add'">
          增加了 {{ item.edcname }} {{ item.projectName}}条目。
        </li>
        <li v-if="item.flag === 'delete'">
          删除了 {{ item.edcname }} {{ item.projectName}}条目。
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { getOldMasterData, getNewMasterData } from "@/api/dataEntry/data";
import lodash from "lodash";

export default {
  name: "dataEntry",
  data() {
    return {
      oldLoading: false,
      newLoading: false,
      buttonDisable: false,
      showResult: false,
      oldMasterData: [],
      newMasterData: [],
      compareResult: [],
      fieldList: ['id', 'projectName', 'price', 'taxes', 'managementFree']
    };
  },
  created() {
    this.getOldList();
    this.getNewList();
  },
  methods: {
    // 获取已有的 MasterData
    getOldList() {
      this.oldLoading = true;
      getOldMasterData().then((response) => {
        this.oldMasterData = response;
        this.oldLoading = false;
      });
    },
    // 获取用户上传的新的 MasterData
    getNewList() {
      this.newLoading = true;
      getNewMasterData().then((response) => {
        this.newMasterData = response;
        this.newLoading = false;
      });
    },
    // 获取对比结果
    getCompareResult() {
      // oldMasterData 里有的，newMasterData 里没有的，就是用户删除的
      this.oldMasterData.forEach((oldObj) => {
        const newObj = this.newMasterData.find((newObj) => newObj.edcname === oldObj.edcname);

        if (!newObj) {
          var obj = {
            edcname: oldObj.edcname,
            projectName: oldObj.projectName,
            flag: 'delete'
          };
          this.compareResult.push(obj);
        }
      });

      // newMasterData 里有的，oldMasterData 里没有的，就是用户新增的
      // 用 newMasteDta 去对比 oldMasterData
      this.newMasterData.forEach((newObj, index) => {
        const oldObj = this.oldMasterData.find((oldObj) => oldObj.edcname === newObj.edcname);

        if (oldObj) {
          var oldArr = [oldObj]
          var newArr = [newObj]
          this.compare(oldArr, newArr);
        } else {
          var obj = {
            edcname: this.newMasterData[index].edcname,
            projectName: this.newMasterData[index].projectName,
            flag: 'add'
          };
          this.compareResult.push(obj);
        }
      });
    },
    compare(oldObj, newObj) {
      // 开始对比 
      console.log(oldObj,newObj)

      this.fieldList.forEach((field) => {
        console.log('当前对比的字段的是', field)
        let result = lodash.differenceBy(newObj, oldObj, field)

        if (result.length != 0) {
          var fieldName = "";
          switch (field) {
            case "edcname":
              fieldName = "EDC";
              break;
            case "projectName":
              fieldName = "项目";
              break;
            case "price":
              fieldName = "单价";
              break;
            case "taxes":
              fieldName = "税费";
              break;
            case "managementFree":
              fieldName = "管理费";
              break;
          }

          var obj = {
            id: result[0].id,
            projectName: result[0].projectName,
            field: fieldName,
            new: result[0][field],
            flag: 'update'
          };
          this.compareResult.push(obj);
        }
      });
      console.log('对比结果', this.compareResult);
      this.buttonDisable = true;
      this.showResult = true;
    }
  },
};
</script>

<style>
.box-card {
  margin-top: 50px;
  width: 100%;
}
</style>
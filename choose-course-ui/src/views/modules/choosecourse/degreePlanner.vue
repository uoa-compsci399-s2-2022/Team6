<template>
  <div style="background-color: #ffffff !important">
    <el-row>
      <!-- 左边部分 -->
      <el-col :span="12" class="solid-bd">

        <!-- stage1 -->
        <el-row style="padding: 10px;">
          <div>year1</div>
          <el-col style="padding: 10px;" class="solid-bd">
            <!-- <el-button v-for="(item,index) in 5">1</el-button> -->
            <div>
              <el-button v-for="(item,index) in stageOneList" :key="index" border size="mini">{{item}}
              </el-button>
            </div>

          </el-col>
        </el-row>

        <!-- stage2 -->
        <el-row style="padding: 10px;">
          <div>year2</div>
          <el-col style="padding: 10px;" class="solid-bd">
            <!-- <el-button v-for="(item,index) in 5">1</el-button> -->
            <div>
              <el-button v-for="(item,index) in stageTwoList" :key="index" border size="mini">{{item}}
              </el-button>
            </div>

          </el-col>
        </el-row>

        <!-- stage3 -->
        <el-row style="padding: 10px;">
          <div>year3</div>
          <el-col style="padding: 10px;" class="solid-bd">
            <!-- <el-button v-for="(item,index) in 5">1</el-button> -->
            <div>
              <el-button v-for="(item,index) in stageThreeList" :key="index" border size="mini">{{item}}
              </el-button>
            </div>

          </el-col>
        </el-row>

        <!-- GE -->
        <el-row style="padding: 10px;">
          <div>GE</div>
          <el-col style="padding: 10px;" class="solid-bd">
            <!-- <el-button v-for="(item,index) in 5">1</el-button> -->
            <div>
              <el-button v-for="(item,index) in geList" :key="index" border size="mini">{{item}}
              </el-button>
            </div>

          </el-col>
        </el-row>

        <div  style="margin-top: 100px;">
          <strong>Course Remark</strong>
          <el-input
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 4}"
              placeholder="remark"
              v-model="remark">
          </el-input>
          <div  style="margin-top: 10px;"></div>
          <el-button type="primary" @click="submitData()">submit</el-button>
          <el-button type="primary" @click="clearSelection()">clear selection</el-button>
          <el-button type="primary" @click="exportHandle()">export</el-button>
        </div>
      </el-col>
      <!-- 右边部分 -->
      <el-col :span="12" class="solid-bd">
        <strong>1.select stage:</strong>
        <el-radio-group v-model="selectStage" @change="stageChange($event)">
          <el-radio-button label="stage1"></el-radio-button>
          <el-radio-button label="stage2"></el-radio-button>
          <el-radio-button label="stage3"></el-radio-button>
          <el-radio-button label="ge"></el-radio-button>
        </el-radio-group>
        <br />
        <strong>2.select major:</strong>
        <el-select v-model="majorOne" placeholder="major1" @change="majorOneChange($event,'stage1')" :disabled = "selectStage === 'ge'" size="mini" class="right-select">
          <el-option v-for="item in majorOneList"  :key="item" :label="item" :value="item">
          </el-option>
        </el-select>
        <el-select v-model="majorTwo" @change="majorTwoChange($event,'stage2')" placeholder="major2" :disabled = "selectStage === 'ge'" size="mini" class="right-select">
          <el-option v-for="item in majorTwoList" :key="item" :label="item" :value="item">
          </el-option>
        </el-select>
        <!-- <el-select v-model="majorThree" @change="geChange()" placeholder="GE" size="mini" class="right-select">
          <el-option v-for="item in majorThreeList" :key="item" :label="item" :value="item">
          </el-option>
        </el-select> -->
        <div>
        </div>
        <strong>3.select course:</strong>
        <br>
        <el-radio @change="courseChange(item)" :label="item" v-for="item in allCourseList" :key="item">{{item}}</el-radio>

        <div  style="margin-top: 200px;">
          <div style="font-size: 12px;margin-top: 20px;">
            <strong>Major1 Requirement</strong>
            <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                placeholder="major1Remark"
                v-html="major1Remark">
            </el-input>
          </div>

          <div style="font-size: 12px;margin-top: 50px;">
            <strong>Major2 Requirement</strong>
            <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                placeholder="major2Remark"
                v-html="major2Remark">
            </el-input>
            <div style="font-size: 12px;margin-top: 50px;"></div>
            <B><br/>1. Courses in a minimum of three subject codes listed in the BSc Schedule.
              <br/>2. At least 180 points (12 courses) must be above Stage I.
              <br/>3. At least 75 points must be at Stage III, of which 45 points must be in the majoring subject, and 15 points from a capstone course listed in the BSc Schedule.
              <br/>4. 30 points (2 courses) must be taken from the appropriate General Education Schedules for BSc students.
              <br/>5. Up to 30 points (2 courses) may be taken from outside the Faculty.
            </B>
          </div>
        </div>

      </el-col>
    </el-row>

  </div>
</template>

<script>
import user from '../../../store/modules/user'
import qs from 'qs'
import Cookies from 'js-cookie'
export default {
  data() {
    return {
      remark:"",
      major1Remark:"",
      major2Remark:"",
      stageList: ["stage1", "stage2", "stage3"],
      selectStage: "stage1",
      stageOneList: [],
      stageTwoList: [],
      stageThreeList: [],
      geList: [],
      majorOne: "",
      majorTwo: "",
      majorThree: "",
      majorOneList: [],
      majorTwoList: [],
      majorThreeList: ["GE"],
      courseOneList:[],
      courseTwoList:[],
      courseThreeList:[],
      allCourseList: [],
      selectCourseList: [],
      userName:""
    }
  },
  mounted() {
    this.$http.get('/sys/user/info').then(({ data: res }) => {
      if (res.code !== 0) {
        return this.$message.error(res.msg)
      }
      // this.$store.state.user.id = res.data.id
      this.userName = res.data.username
      // this.$store.state.user.superAdmin = res.data.superAdmin
      this.getStageList()
      this.getAllMajor()
    }).catch(() => {})

  },
  methods: {
    getAllMajor(){
      debugger
      const params = {
        major : "GE",
      }
      this.$http.post('/examination/usercourse/getAllMajorByType', params)
          .then(({ data: res }) => {
            if (res.code !== 0) {

            }
            this.majorOneList = res.data
            this.majorTwoList = res.data
          }).catch(() => {})

    },
    exportHandle () {
      this.$http.get('/sys/user/info').then(({ data: res }) => {
        if (res.code !== 0) {
          return this.$message.error(res.msg)
        }
        // this.submitData()
        let params = qs.stringify({
          'token': Cookies.get('token'),
          "userName": res.data.username
        })
        window.location.href = `${window.SITE_CONFIG['apiURL']}/examination/usercourse/exportUserCourse?${params}`
      }).catch(() => {})

    },
    clearSelection(){
      this.stageOneList = []
      this.stageTwoList = []
      this.stageThreeList = []
      this.geList = []
    },
    getStageList(){
      debugger
      console.log(this.userName)
      const params = {
        userName : this.userName
      }
      this.$http.post('/examination/usercourse/getStageByUser', params)
          .then(({ data: res }) => {
            if (res.code !== 0) {
              return this.$message.error(res.msg)
            }
            debugger
            if(res.data.stage1 !== ""){
              this.stageOneList = res.data.stage1.split(",")
            }
            if(res.data.stage2 !== ""){
              this.stageTwoList = res.data.stage2.split(",")
            }
            if(res.data.stage3 !== ""){
              this.stageThreeList = res.data.stage3.split(",")
            }
            if(res.data.ge !== ""){
              this.geList = res.data.ge.split(",")
            }
            // const a =
            // res.data.stage1.split(",")
            a.forEach((item)=>{
              console.log(item);
            })
            this.$message({
              message: this.$t('prompt.success'),
              type: 'success',
              duration: 500,
              onClose: () => {

              }
            })
          }).catch(() => {})
    },
    geChange(){
      debugger
      const params = {
        major : "GE",
      }
      this.$http.post('/examination/course/getCourseByMajor', params)
          .then(({ data: res }) => {
            if (res.code !== 0) {

            }
            this.allCourseList = res.data.list
          }).catch(() => {})

    },
    stageChange(e) {
      this.allCourseList = []
      this.majorOne = ""
      this.majorTwo = ""
      this.majorThree = ""
      if(e === "ge"){
        this.geChange()
      }
      // this.majorOneList = []
      // this.majorTwoList = []
      // this.majorThreeList = []
    },

    clearCourseOne(){
      this.courseOneList.forEach((item,index) => {
        this.allCourseList.filter(item)
      })
      this.courseOneList = []
    },
    clearCourseTwo(){
      this.courseTwoList.forEach((item,index) => {
        this.allCourseList.filter(item)
      })
      this.courseTwoList = []
    },
    clearCourseThree(){
      this.courseThreeList.forEach((item,index) => {
        this.allCourseList.filter(item)
      })
      this.courseThreeList = []
    },
    majorOneChange(major){
      debugger
      const params = {
        major : major,
        stage : this.selectStage
      }
      this.$http.post('/examination/course/getCourseByMajor', params)
          .then(({ data: res }) => {
            if (res.code !== 0) {

            }
            this.allCourseList = res.data.list
            this.major1Remark =  res.data.majorRemark
          }).catch(() => {})

    },
    majorTwoChange(major){
      debugger
      const params = {
        major : major,
        stage : this.selectStage
      }
      this.$http.post('/examination/course/getCourseByMajor', params)
          .then(({ data: res }) => {
            if (res.code !== 0) {

            }
            this.allCourseList = res.data.list
            this.major2Remark =  res.data.majorRemark
          }).catch(() => {})

    },
    getCourseList(majorType){
      const param = {
        major : majorType
      }
      this.$http.post('/examination/usercourse', param)
          .then(({ data: res }) => {
            if (res.code !== 0) {
              return this.$message.error(res.msg)
            }
            this.$message({
              message: this.$t('prompt.success'),
              type: 'success',
              duration: 500,
              onClose: () => {

              }
            })
          }).catch(() => {})
    },
    courseChange(selectCourse){
      debugger
      if(this.selectStage === "stage1"){
        if(this.stageOneList.length > 7){
          return this.$message({
            message:"Select no more than 8 courses",
            type: 'error',
            duration: 2000,
          })
        }
        if(!this.stageOneList.includes(selectCourse)){
          this.stageOneList.push(selectCourse)
        }else{
          this.$message({
            message: "This course has already been selected!",
            type: 'warning',
            duration: 2000
          })
        }
      }else if(this.selectStage === "stage2"){
        if(this.stageTwoList.length >= 7){
          return this.$message({
            message:"Select no more than 7 courses",
            type: 'error',
            duration: 2000,
          })
        }
        if(!this.stageTwoList.includes(selectCourse)){
          this.stageTwoList.push(selectCourse)
        }else{
          this.$message({
            message: "This course has already been selected!",
            type: 'warning',
            duration: 2000
          })
        }
      }else if(this.selectStage === "stage3"){
        if(this.stageThreeList.length >= 7){
          return this.$message({
            message:"Select no more than 7 courses",
            type: 'error',
            duration: 2000,
          })
        }
        if(!this.stageThreeList.includes(selectCourse)){
          this.stageThreeList.push(selectCourse)

        }else{
          this.$message({
            message: "This course has already been selected!",
            type: 'warning',
            duration: 2000
          })
        }
      }else if(this.selectStage === "ge"){
        if(this.geList.length >= 2){
          return this.$message({
            message:"GE course select no more than 2 courses",
            type: 'error',
            duration: 2000,
          })
        }
        if(!this.geList.includes(selectCourse)){
          this.geList.push(selectCourse)

        }else{
          this.$message({
            message: "This course has already been selected!",
            type: 'warning',
            duration: 2000
          })
        }
      }
      debugger
      console.log(selectCourse);
      const params = {
        // major : major,
        stage : this.selectStage,
        course : selectCourse
      }
      this.$http.post('/examination/course/getCourseRemarkByMajor', params)
          .then(({ data: res }) => {
            if (res.code !== 0) {

            }
            this.remark = res.data
          }).catch(() => {})


    },
    selectChange(e,item) {
      debugger
      if(item === "BIOSCI 100" && e){
        this.remark = " Antarctica: The Frozen Continent No pre-requisites or restrictions"
      }else{
        this.remark = ""
      }
      // this.stageOneList = e
    },
    submitData(){
      let stage1 = this.stageOneList.join()
      let stage2 = this.stageTwoList.join()
      let stage3 = this.stageThreeList.join()
      let ge = this.geList.join()
      this.$http.get('/sys/user/info').then(({ data: res }) => {
        debugger

        if (res.code !== 0) {
          return this.$message.error(res.msg)
        }
        const params = {
          stage1 : stage1,
          stage2 : stage2,
          stage3 :  stage3,
          ge :  ge,
          userName : res.data.username
        }
        this.$http.post('/examination/usercourse/insert', params)
            .then(({ data: res }) => {
              if (res.code !== 0) {
                return this.$message.error(res.msg)
              }
              this.getStageList()
              this.$message({
                message: "Submit Successful",
                type: 'success',
                duration: 500,
                onClose: () => {

                }
              })
            }).catch(() => {})
      }).catch(() => {})


    }
  }
}
</script>

<style>
.solid-bd {
  border: 1px solid #bcbcbc;
  height: 100%;
  padding: 10px;
}

.right-select {
  padding: 10px;
  width: 135px;
  margin-left: 10px;
}
</style>

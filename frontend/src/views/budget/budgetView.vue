<template>
  <div>
    <h1>이번달 나의 예산</h1>
    
    <h3 style="display:flex; margin-top:-10px; margin-left: 20px;">총 예산: {{ priceToString(req.total_budget) }} ₩</h3>

    <div class="baseBar">
      <div class="gaugeBar"></div>
    </div>

    <div id="budgetTitle" style="font-size: 17px;">
      <div> <span style="font-weight:bold;">사용 예산:</span> {{ priceToString(req.spend_budget) }} ₩</div>
    </div>
    <div id="budgetTitle" style="font-size: 17px;">
      <div> <span style="font-weight:bold;">남은 예산:</span> {{ priceToString(req.total_budget - req.spend_budget) }} ₩</div>
    </div>
    <div id="budgetTitle" style="font-size: 17px;">
      <div> <span style="font-weight:bold;">오늘까지 권장 지출액:</span> 1,200,000 ₩</div>
    </div>
    
    <button v-on:click="this.$router.push('/budget/set')">예산 설정하기</button>

    <div style="margin-top:45px;">
      <h3 id="budgetTitle">카테고리별 예산</h3>
      <div v-for="(budget_category, idx) in req.budget_categories" :key="idx" class="cell">
        <div>
          <div id="budgetTitle" style="display:flex; align-items:center; justify-content: space-between;">
            <div style="display:flex; align-items:center;">
              <div id="budgetImg"></div>
              <div style="font-weight: bold; margin-left: 10px; font-size: 16px;">{{budget_category.category}}</div>
            </div>
            <div style="color:#808080;">{{ priceToString(100 - (budget_category.total_budget - budget_category.spend_budget) / budget_category.total_budget * 100) }}%</div>
            <div style="font-weight: bold;">{{ priceToString(budget_category.total_budget - budget_category.spend_budget) }}원 남음 </div>
          </div>


          <!-- [{{budget_category.category}}] | 
          {{ priceToString(100 - (budget_category.total_budget - budget_category.spend_budget) / budget_category.total_budget * 100) }}% | 
          {{ priceToString(budget_category.total_budget - budget_category.spend_budget) }}원 남음 -->
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      year: new Date().getFullYear(),
      month: new Date().getMonth()+1,
      data: 0,
      req: {
        total_budget: 2000000,
        spend_budget: 1000000,
        budget_categories:[
        {
            category:'쇼핑',
            total_budget: 500000,
            spend_budget: 400000,
          },
          {
            category: '여행',
            total_budget: 400000,
            spend_budget: 600000,
          },
          {
            category: '식사',
            total_budget: 1100000,
            spend_budget: 800000,
          }
        ]
      }
    }
  },

  methods: {
    getData(){
      this.axios.get(process.env.VUE_APP_API_URL + `/account-book/budget/${this.year}/${this.month}`)
      .then(res => {
        this.data = res.data;
        console.log(res.data);
      }).then(() => {
        if (this.data.amount == 1) {
          // this.axios.get(process.env.VUE_APP_API_URL + `/`)
          this.$router.push('/budget/set')
        }
      })
    },
    priceToString(price) {
      return Math.round(price).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    }
  },
  mounted() {
    this.getData()
    const gauge = document.querySelector(".gaugeBar");
    let tmp = 100 - (this.req.total_budget - this.req.spend_budget) / this.req.total_budget * 100
    gauge.style.width = `${tmp}%`;
  }

}
</script>

<style>
.baseBar {
  width: 300px;
  height: 28px;
  background-color: #E5E5E5;
  margin: 0 auto;
  padding: 2px;
  border-radius: 25px;
}

.gaugeBar {
  width: 200px;
  height: 28px;
  background-color: #4D82E6;
  border-radius: 25px;
}

.cell {
  display: contents;
  align-items: center;
}

#budgetTitle {
  display:flex;
  margin-left: 20px;
  margin-top: 10px;
}

#budgetImg {
  background-color:#DEF0FF; width:35px; height:35px;
  border-radius: 20px;
}
</style>
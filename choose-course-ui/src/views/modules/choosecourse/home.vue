<template>
	<div>
		<!-- <img src="../../../assets/img/1.jpg" alt="" srcset="">
		 <img src="../../../assets/img/2.jpg" alt="" srcset=""> -->
		 <!-- <img src="../../../assets/img/3.jpg" alt="" srcset=""> -->
		 <img src="../../../assets/img/5.jpg" alt="" srcset="">
	</div>
</template>
<script>
	export default {
		data() {
			return {
				hotteams: [{
						id: 11,
						name: 'zzy',
						sort: 1
					},
					{
						id: 22,
						name: 'lt',
						sort: 2
					},
					{
						id: 33,
						name: 'wzg',
						sort: 3
					}
				],
				dragIdx: ''
			}
		},
		methods: {
			dragStart(e){
				debugger
				console.log(e);
				
			},
			addTeam(team) {
				this.hotteams.push(team)
			},
			delTeam(idx) {
				this.hotteams.splice(idx, 1)
			},
			dragstart(e, index) {
				this.dragIdx = index
			},
			dragDrop(e, index) {
				let _teams = this.hotteams //Save the hotteams
				let _dragitem = _teams[this.dragIdx] //Saves the data that was dragged
				_teams.splice(this.dragIdx, 1) //Delete the data that is being dragged
				_teams.splice(index, 0, _dragitem) //Drops the data being dragged to the specified location in the array
			}
		}
	}
</script>
<style scoped>
</style>

<template>
	<div>
		
		<div style="float: left; margin-left: 0%; width: 100%; height: 500px;">
			<img src="../assets/index/index1.jpg" style="width: 100%;height: 100%;">
		</div>

		<div style="float: left; margin-top: 40px;margin-left: 10%; width: 80%; height: 100px; display: flex; flex-direction: row; justify-content: space-between;">
			<div style="width: 200px; height: 80px;">
				<h2>系统管理</h2>
				<el-button type="primary">进入系统管理</el-button>
			</div>
			<div style="width: 200px; height: 80px;">
				<h2>模型管理</h2>
				<el-button type="primary">进入模型管理</el-button>
			</div>
			<div style="width: 200px; height: 80px;">
				<h2>接口管理</h2>
				<el-button type="primary">进入接口管理</el-button>
			</div>
		</div>

	</div>
</template>

<script>
	export default {
		name: "Index",
	}
</script>

<style scoped>



</style>
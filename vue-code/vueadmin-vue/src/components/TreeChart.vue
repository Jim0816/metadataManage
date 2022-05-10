<template>
    <div :style="{width: myGraphPanelSize.width + 'px',height:myGraphPanelSize.height + 'px'}" style="float:left;">
        <seeksRelationGraph
                ref="seeksRelationGraph"
                :options="graphOptions"
                :on-node-click="onNodeClick"
                :on-line-click="onLineClick">
            <div style="width: 100%;height: 100%;" slot="node" slot-scope="{node}">
                <!--<div style="height:64px;line-height: 64px;border-radius: 32px;cursor: pointer;">
                    <i style="font-size: 30px;" :class="node.data.myicon" />
                </div>-->
                <div style="float:left;width:100%;height:100%;color:white;font-size:14px;line-height: 20px;margin-top:5px;text-align: center;" @contextmenu.prevent.stop="showNodeMenus(node, $event)">
                    {{ node.data.nodeType != 3 ? node.data.nodeName : node.data.fieldInfo.fieldName}}
                </div>
            </div>
        </seeksRelationGraph>

        <!--操作节点-->
        <el-dialog
                title="操作节点"
                :visible.sync="viewNodeDialog">
            <div style="width:100%;height: 500px;overflow-y: scroll;" >
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import seeksRelationGraph from 'relation-graph'
    export default {
        name: 'TreeChart',
        components: { seeksRelationGraph },
        props: ["tree", "treeChangeFlag", "treeSize"],
        watch: {
            treeChangeFlag: {
                handler (n, o) {
                    console.log("父组件传递的数据发生变化", this.tree)
                    // 刷新图
                    this.showSeeksGraph()
                },
                deep: true // 深度监听父组件传过来对象变化
            }
        },
        computed: {},
        data() {
            return {
                viewNodeDialog: false,
                myGraphPanelSize: { width: 400, height: 400 },
                isShowNodeMenuPanel: false,
                nodeMenuPanelPosition: { x: 0, y: 0 },
                currentNode: {},
                graphOptions: {
                    "backgrounImage": "",
                    "backgrounImageNoRepeat": true,
                    "layouts": [
                        {
                            "label": "中心",
                            "layoutName": "tree",
                            "layoutClassName": "seeks-layout-center",
                            "defaultJunctionPoint": "border",
                            "defaultNodeShape": 0,
                            "defaultLineShape": 1,
                            "centerOffset_x": "0",
                            "centerOffset_y": "0",
                            "min_per_width": "500",
                            "min_per_width": "1000",
                            "min_per_height": "110",
                            "max_per_height": "500"
                        }
                    ],
                    "defaultLineMarker": {
                        "markerWidth": 0,
                        "markerHeight": 0,
                        "refX": 0,
                        "refY": 0,
                    },
                    "defaultLineWidth": 2,
                    "defaultLineShape": 4,
                    "defaultNodeShape": 1,
                    "defaultNodeWidth": "100",
                    "defaultNodeHeight": "30",
                    "defaultNodeBorderWidth": 0,
                    "allowShowMiniToolBar": true,
                    "defaultExpandHolderPosition": "top",
                    "defaultJunctionPoint": "tb"
                }
            }
        },
        mounted() {
            this.showSeeksGraph()
            //this.transformTree(this.tree)
        },
        methods: {
            showSeeksGraph(query) {
                //let data = this.transformTree(this.tree)
                console.log(this.tree)
                this.$refs.seeksRelationGraph.setJsonData(this.tree, (seeksRGGraph) => {
                    // 这些写上当图谱初始化完成后需要执行的代码
                })

                this.$refs.seeksRelationGraph.setOptions(this.graphOptions, (seeksRGGraph) => {
                    // 这些写上当图谱初始化完成后需要执行的代码
                    console.log('setOptions:callback:', seeksRGGraph)
                    // seeksRGGraph.refresh()
                })

                this.myGraphPanelSize = this.treeSize
            },
            onNodeClick(nodeObject, $event) {
                //this.$emit('getTreeMapData',nodeObject.data)
            },
            onLineClick(lineObject, $event) {
                console.log('onLineClick:', lineObject)
            },
            showNodeMenus(nodeObject, $event) {
                /*this.currentNode = nodeObject
                var _base_position = this.$refs.seeksRelationGraph
                //console.log('showNodeMenus:', $event, _base_position)
                this.isShowNodeMenuPanel = true
                this.nodeMenuPanelPosition.x = $event.clientX
                this.nodeMenuPanelPosition.y = $event.clientY*/
                this.$emit('getTreeMapData',nodeObject.data)
            },
            onSizeOptionChanged() {
                // 图谱的大小会随着父元素的宽高自动适应，所以修改父元素的宽高就可以设置图谱的宽高
                this.myGraphPanelSize.width = this.currentSize
                this.myGraphPanelSize.height = this.currentSize
                // 你也可以在调整完大小后刷新一下图谱，确保位置是合理的
                // this.$nextTick(() => { // $nextTick的功能你懂的
                //   this.$refs.seeksRelationGraph.refresh()
                // })
            }
        }
    }
</script>

<style lang="scss" scoped>
</style>

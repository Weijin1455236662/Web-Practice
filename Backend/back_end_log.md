# BackEndLog

- 2020/10/26

  - 添加自定义排程接口public Result scheduling(@PathVariable String beginDate,@PathVariable String endDate )；

    - 功能

      - 根据数据库中已有成员列表、设备列表、工艺列表、订单列表，以满足给定截止日期前所有订单均可按期交付为目的进行排程（精确到小时），如果修改原有数据表或输入时期，再次调用该接口可重新排程

    - 输入

      - 起始日期，截止日期

    - 输出

      - {

          "flag（成功或失败标志）",

          "message（成功或失败信息）",

          "data（排程结果）": [

        ​		{

        ​			"item（某天排程结果）":[

        ​				[（某小时排程结果）

        ​					{

        ​						"equipmentid（设备id）":[

        ​							"material_code（物料编号）"

        ​							"teamid（小组id）",

        ​							...

        ​						]

        ​					},

        ​					...

        ​				],

        ​				...

        ​			],

        ​			"date（该天日期）"

        ​		},

        ​		...

        ​	]
        
        }

- 2020/11/3

  - 添加基于开源线性规划求解器OptaPlanner的排程接口public Result solve(@PathVariable String beginDate, @PathVariable String endDate );（GetMapping("/arrangement/{beginDate}/{endDate}")）（仍保留历史接口public Result scheduling(@PathVariable String beginDate,@PathVariable String endDate )；（GetMapping("/schedule/{beginDate}/{endDate}")）作为参考，系统正式上线前会删除）

    - 功能

      - 根据数据库中已有成员列表、设备列表、工艺列表、订单列表，以尽可能满足给定截止日期前所有订单均可按期交付为目的进行排程（精确到小时），根据输入的起始日期，截止日期不同可能会导致部分订单延期（目前尚未实现手动插单不得延期），如果修改原有数据表或输入时期，再次调用该接口可重新排程

    - 输入

      - 起始日期，截止日期

    - 输出

      - {

        ​	"flag（成功或失败标志）",

        ​	"message（成功或失败信息）",

        ​	"data":{

        ​		"teamLists":[],

        ​		"equipmentList":[],

        ​		"timeslotList":[],

        ​		"subOrderList(子订单列表)":[

        ​			{
        ​                "id"（子订单id）: 1,
        ​                "parent_id"（父订单id）: 1,
        ​                "material_code"（物料编号）: 1,
        ​                "quantity"（物料生产数）: 50,
        ​                "capacity"（生产所需人数）: 5,
        ​                "teamList"（合作组列表）: {
        ​                    "teamList（合作组列表）": [
        ​                        {
        ​                            "teamid": 2,
        ​                            "name": "小红",
        ​                            "num": 4,
        ​                            "begin_day": 1,
        ​                            "end_day": 5,
        ​                            "begin_time": 7,
        ​                            "end_time": 20
        ​                        },
        ​                        ...
        ​                    ]
        ​                },
        ​                "equipment（使用设备）": {
        ​                    "equipmentid": 1,
        ​                    "name": "lineA",
        ​                    "type": "line",
        ​                    "amount": 1
        ​                },
        ​                "timeslot（时间与日期）": {
        ​                    "time": 7,
        ​                    "date": "2020-10-27"
        ​                }

        ​			},

        ​			...

        ​		]，

        ​		"score": ""

        ​	}

        }
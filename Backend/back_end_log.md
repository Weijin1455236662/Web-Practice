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
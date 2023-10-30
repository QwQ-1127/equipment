export const menus = [
    {
        index: "常用",
        icon: "platform-eleme",
        children: [
            {
                path: "/equipment/list",
                menu: "设备管理",
                have: ["管理员"],
            },
            {
                path: "/equipment/baoxiu",
                menu: "设备报修",
                have: ["用户", "管理员"],
            },
        ],
    },
    {
        index: "设置",
        icon: "setting",
        children: [
            {
                path: "/my/my",
                menu: "个人中心",
                have: ["用户", "管理员"],
            },
        ],
    },
]
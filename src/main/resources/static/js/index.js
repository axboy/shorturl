let vue = new Vue({
    el: "#vue-app",
    data: {
        resp: {},
        modalData: {},
        pageData: {
            curPage: 0,
            pageSize: 10,
            totalCount: 0,
            totalPages: 0,
            pageNums: []
        }
    },
    methods: {
        getData(page, size) {
            if (size == null || size < 0) {
                size = vue.pageData.pageSize;
            }
            $.ajax({
                url: `../api/url/page?page=${page}&size=${size}`,
                success: function (data) {
                    vue.resp = data;
                    vue.pageData.curPage = data.number;
                    vue.pageData.pageSize = data.size;
                    vue.pageData.totalCount = data.totalElements;
                    vue.pageData.totalPages = data.totalPages;
                    vue.pageData.pageNums = [];
                    let basePageNum = Math.floor(data.number / 5) * 5;
                    if (basePageNum > 0) {
                        vue.pageData.pageNums.push(basePageNum - 1);
                    }
                    for (let i = 0; i < 6; i++) {
                        let num = basePageNum + i;
                        if (num < data.totalPages) {
                            vue.pageData.pageNums.push(num);
                        }
                    }
                }
            });
        },
        showModal(index) {
            vue.modalData = {};
            if (index >= 0) {
                vue.modalData = vue.resp.content[index];
            }
            $('#myModal').modal({
                keyboard: true
            })
        },
        submit() {
            let url = "../api/url/edit";
            if (!vue.modalData.key || vue.modalData.key == null) {
                url = "../api/url/add";
            }
            $.ajax({
                url: url,
                type: 'post',
                dataType: "text",
                data: {
                    key: vue.modalData.key,
                    url: vue.modalData.sourceUrl
                },
                success: function (data) {
                    //alert("操作成功");
                    vue.getData(vue.pageData.curPage, vue.pageData.pageSize);
                    $('#myModal').modal('hide')
                },
                error: function (data) {
                    alert("源链接错误");
                }
            });
        }
    }
});
vue.getData(0);
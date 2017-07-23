let vue = new Vue({
    el: "#vue-app",
    data: {
        resp: {},
        modalData: {}
    },
    methods: {
        getData(page){
            $.ajax({
                url: `/api/url/page?page=${page}&size=20`,
                success: function (data) {
                    vue.resp = data;
                }
            });
        },
        showModal(index){
            vue.modalData = {};
            if (index >= 0) {
                vue.modalData = vue.resp.content[index];
            }
            $('#myModal').modal({
                keyboard: true
            })
        },
        submit(){
            let url = "/api/url/edit";
            if (!vue.modalData.key || vue.modalData.key == null) {
                url = "/api/url/add";
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
                    alert("修改成功");
                },
                error:function (data) {
                    alert("ERROR");
                }
            });
        }
    }
});
vue.getData(0);
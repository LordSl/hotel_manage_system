<template>
    <a-modal
            :visible="appealReviewModalVisible"
            title="订单撤销原因申诉"
            cancelText="否定"
            okText="批准申诉"
            :footer=null
            @cancel="closeModal"
            @ok="appealAccepted"
    >
        <p>{{appealMessageReview}}</p>
        <br/>
        <a-button type="primary" ghost style="float:right;bottom: 20px; margin-right: 5px" icon="smile" @click="appealAccepted">通过申诉</a-button>
        <a-button type="danger" ghost style="float:right;bottom: 20px;margin-right: 10px" icon="frown" @click="appealDenied">拒绝申诉</a-button>
    </a-modal>
</template>

<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: "appealReviewModal",
        computed: {
            ...mapGetters([
                'appealReviewModalVisible',
                'appealMessageReview'
            ])
        },
        data(){
            return{
            }
        },
        beforeCreate() {
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_appealReviewModalVisible',
            ]),
            ...mapActions([
                'changeAppealStatus'
            ]),
            appealDenied(){
                this.changeAppealStatus("审核未通过")
                this.set_appealReviewModalVisible(false)
            },
            closeModal(){
                this.set_appealReviewModalVisible(false)
            },
            appealAccepted(){
                console.log('nbsszrx')
                this.changeAppealStatus("审核通过")
                this.set_appealReviewModalVisible(false)
            }

        }
    }

</script>

<style>
</style>

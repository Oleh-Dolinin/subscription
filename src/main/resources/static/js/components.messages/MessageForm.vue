<template>
    <div>
        <input type="text" placeholder="Write something" v-model="title" />
        <input type="text" placeholder="Write something" v-model="description" />
        <input type="button" value="Save" @click="save" />
    </div>
</template>

<script>
    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++ ) {
            if (list[i].id === id) {
                return i
            }
        }
        return -1
    }
    export default {
        props: ['messages', 'messageAttr'],
        data() {
            return {
                title: '',
                description: '',
                id: ''
            }
        },
        watch: {
            messageAttr(newVal, oldVal) {
                this.title = newVal.title
                this.description = newVal.description
                this.id = newVal.id
            }
        },
        methods: {
            save() {
                const message = { title: this.title, description: this.description }
                if (this.id) {
                    this.$resource('/news{/id}').update({id: this.id}, message).then(result =>
                        result.json().then(data => {
                            const index = getIndex(this.messages, data.id)
                            this.messages.splice(index, 1, data)
                            this.title = ''
                            this.description = ''
                            this.id = ''
                        })
                    )
                } else {
                    this.$resource('/news{/id}').save({}, message).then(result =>
                        result.json().then(data => {
                            this.messages.push(data)
                            this.title = ''
                            this.description = ''
                        })
                    )
                }
            }
        }
    }
</script>

<style>
</style>
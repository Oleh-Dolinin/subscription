function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}


var messageApi = Vue.resource('/news{/id}');


Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function() {
        return {
            title: '',
            description: '',
            id: ''
        }
    },
    watch: {
        messageAttr: function(newVal, oldVal) {
            this.title = newVal.title;
            this.description = newVal.description;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder="Write something" v-model="title" />' +
            '<input type="text" placeholder="Write something" v-model="description" />' +
            '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var message = { title: this.title, description: this.description };

            if (this.id) {
                messageApi.update({id: this.id}, message).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.messages, data.id);
                        this.messages.splice(index, 1, data);
                        this.title = ''
                        this.description = ''
                        this.id = ''
                    })
                )
            } else {
                messageApi.save({}, message).then(result =>
                    result.json().then(data => {
                        this.messages.push(data);
                        this.title = ''
                        this.description = ''
                    })
                )
            }
        }
    }
});

Vue.component('message-row', {
    props: ['message', 'editMethod', 'messages', 'profile'],
    template: '<div>' +
        '<i>({{ message.id }})</i> {{ message.title }}  {{ message.description }} {{ message.user_id }} ' +
        '<span style="position: absolute; right: 0">' +
                 '<input type="button" value="Edit" @click="edit" />' +
                 '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.message);
        },
        del: function() {
            messageApi.remove({id: this.message.id}).then(result => {
                if (result.ok) {
                    this.messages.splice(this.messages.indexOf(this.message), 1)
                }
            })
        }
    }
});

Vue.component('messages-list', {
  props: ['messages', 'profile'],
  data: function() {
    return {
        message: null
    }
  },
  template:
    '<div style="position: relative; width: 300px;">' +
        '<message-form :messages="messages" :messageAttr="message" />' +
        '<message-row v-for="message in messages" :key="message.id" :message="message" :profile="profile" ' +
            ':editMethod="editMethod" :messages="messages" />' +
    '</div>',
  methods: {
    editMethod: function(message) {
        this.message = message;
    }
  }
});

var app = new Vue({
  el: '#app',
  template:
    '<div>' +
        '<div v-if="!profile">Необходимо авторизоваться через <a href="/login">Google</a></div>' +
        '<div v-else>' +
            '<div>{{profile}}&nbsp;<a href="/logout">Выйти</a></div>' +
            '<messages-list :messages="messages" :profile="profile" />' +
        '</div>' +
    '</div>',
  data: {
    messages: frontendData.messages,
    profile: frontendData.profile
    }
});

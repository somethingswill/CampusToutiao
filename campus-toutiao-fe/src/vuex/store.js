import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

// state在vuex中用于存储数据
let state = {
    adminUserExist: false,
    adminUser: {},
    adminStatus: false,
    navHeaderVisable: true,
    currentUserExist: false,
    currentUser: {},
    newsCategoryList: [],
}

// mutations里边放的是方法，方法主要用于改变state里面的数据
let mutations = {
    toggleAdminstatus(state, flag) {
        state.adminStatus = flag;
    },
    toggleNavHeaderVisable(state, flag) {
        state.navHeaderVisable = flag;
    },
    toggleCurrentUserExist(state, flag) {
        state.currentUserExist = flag;
    },
    toggleAdminUserExist(state, flag) {
        state.adminUserExist = flag;
    },
    injectUserInfo(state, data) {
        state.currentUser = data;
    },
    injectAdminUser(state, data) {
        state.adminUser = data;
    },
    injectNewsCategoryList(state, data) {
        state.newsCategoryList = data;
    },

}

const store = new Vuex.Store({
    state,
    mutations
})

export default store;

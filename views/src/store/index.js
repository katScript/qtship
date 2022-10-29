import { createStore } from 'vuex'
import state from './states'
import getters from './getters'
import mutations from './mutations'
import actions from './actions'

// Create a new store instance.
const store = createStore({
    state,
    getters,
    mutations,
    actions
})


export default store;
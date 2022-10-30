import { createStore } from 'vuex'

// Create a new store instance.
const store = createStore({
    // state,
    // getters,
    // mutations,
    // actions

    state(){
        return {
            product: {
                idProductUpdate: 0
            }
        }
    },
    getters: {
        getProductIdUpdate: (state) => state.product.idProductUpdate
    },
    mutations: {
        SET_PRODUCT_ID_UPDATE(state, id) {
            state.product.idProductUpdate = id;
        }
    },
    actions: {
        setProductIdForUpdate({commit}, id) {
            commit("SET_PRODUCT_ID_UPDATE", id)
        }
    }
})


export default store;
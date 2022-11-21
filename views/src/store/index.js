import { createStore } from 'vuex'

// Create a new store instance.
const store = createStore({
  // state,
  // getters,
  // mutations,
  // actions

  state() {
    return {
      product: {
        idProductUpdate: 0
      },
      loading: false,
    }
  },
  getters: {
    isLoading: (state) => state.loading,
    getProductIdUpdate: (state) => state.product.idProductUpdate
  },
  mutations: {
    SET_PRODUCT_ID_UPDATE(state, id) {
      state.product.idProductUpdate = id;
    },
    SET_LOADING(state, value) {
      state.loading = value;
    }
  },
  actions: {
    setProductIdForUpdate({ commit }, id) {
      commit("SET_PRODUCT_ID_UPDATE", id)
    },
    setLoading({ commit }, value) {
      commit("SET_LOADING", value)
    }
  }
})


export default store;
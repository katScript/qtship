export default {
    changeTypeFlagLogin({ context }, flag) {
        context.commit('setFlagLogin', flag);
    }
}
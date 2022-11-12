export default class LoginService {
    model = {
        username: "",
        password: ""
    }

    data = {
        id: null,
        username: "",
        email: "",
        roles: [],
        accessToken: "",
        tokenType: ""
    }

    errors = []

    setModel(data) {
        this.model.username = data.username;
        this.model.password = data.password;
    }

    getModel() {
        return this.model;
    }

    setData(data) {
        this.data.id = data.id;
        this.data.username = data.username;
        this.data.email = data.email;
        this.data.roles = data.roles;
        this.data.accessToken = data.accessToken;
        this.data.tokenType = data.tokenType;
    }

    getData() {
        return this.data;
    }

    isAuth() {
        return this.getData().accessToken != null;
    }

    validate() {
        let valid = true;

        if (!this.getModel().username || this.getModel().username === "") {

            valid = false;
        }

        if (!this.getModel().password || this.getModel().password === "") {

            valid = false;
        }

        return valid;
    }
}
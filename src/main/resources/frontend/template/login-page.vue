<template>
    <div class="login-page">
        <div class="inst-img">
            <img src=images/inst.jpg alt="Inst image"/>
        </div>
        <div class="login-main-container">
            <div class="login-container">
                <div class="logo-section">
                    <img src=images/5a4e432a2da5ad73df7efe7a.png alt="Instagram Logo"/>
                </div>

                <form>
                    <input type="text" v-model="username" placeholder="username"/>
                    <input type="password" v-model="password" placeholder="password"/>
                    <button @click="login" id="submit">Log in</button>
                </form>

                <div class="forgot-password">
                    <a href="#">Forgot password?</a>
                </div>
            </div>
            <br><div class="signup-section">
                <span> Don't have an account? <a href="#">Sign up</a></span>
            </div>
        </div>
    </div>
</template>

<script>
import './styles/login-page.css';

export default {
    data() {
        return {username: '', password: ''};
    },
    methods: {
        async login() {
            localStorage.setItem('Authentication', btoa(`${this.username}:${this.password}`));
            this.$router.push({name: "profile", params: {username: this.username}})
        }
    }
}
</script>

<style scoped></style>
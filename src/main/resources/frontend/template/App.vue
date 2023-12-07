<template>
    <div class="content">
        <Sidebar/>
        <header class="header">
            <div class="profile">
                <div class="profile-picture" @click="triggerFileInput">
                    <img :src="profilePictureUrl" alt="Profile Picture">
                </div>
                <input type="file" ref="fileInput" @change="uploadProfilePicture" style="display: none;"/>
                <div class="profile-info">
                    <div class="profile-details">
                        <div class="username-wrapper">
                            <p class="profile-username">
                                {{ profileData ? profileData.username : 'Loading...' }}
                            </p>
                        </div>
                        <router-link to="null" class="edit-profile-link">Edit profile</router-link>
                        <router-link to="/archive" class="archive-view-link">View Archive</router-link>
                    </div>
                    <div class="profile-stats">
                        <span><strong>{{ postCount }}</strong> posts</span>
                        <span><strong>500</strong> followers</span>
                        <span><strong>300</strong> following</span>
                    </div>
                    <div class="name">
                        <span id="name">{{ profileData ? profileData.name : 'Loading...' }}</span>
                    </div>
                    <div class="profile-bio">
                        <span id="bio">{{ profileData ? profileData.description : 'Loading...' }}</span>
                    </div>

                </div>
            </div>
        </header>

        <main class="main">

            <hr>
            <p class="center-text">&#9638 <small>POSTS</small></p>
            <div class="photo-grid">
                <div v-for="post in posts" :key="post.id">
                    <img :src="`data:image/jpeg;base64,${post.media}`" :alt="post.description">
                </div>
            </div>
        </main>
    </div>
</template>


<script>
import axios from 'axios';
import './styles/app.css';
import Sidebar from "./Sidebar.vue";

export default {
    components: {
        Sidebar,
    },
    name: 'App',
    data() {
        return {
            posts: [],
            postCount: null,
            profileData: null,
            profilePictureUrl: 'https://via.placeholder.com/80',
        };
    },

    computed: {
        username() {
            return this.$route.params.username;
        }
    },
    methods: {
        async getByUsername() {
            try {
                const response = await axios.get(
                    `http://localhost:8080/profile/getByUsername?username=${this.username}`, {
                        headers: {
                            'Authorization': 'Basic ' + localStorage.getItem('Authentication')
                        }
                    })
                this.profileData = response.data;
                this.posts = response.data.posts || [];
                this.postCount = this.posts.length
                localStorage.setItem('username', this.profileData.username)
                localStorage.setItem('profileId', this.profileData.profileId);
                localStorage.setItem('profile-picture', this.profileData.picture)


                if (response.data.picture) {
                    this.profilePictureUrl = `data:image/jpeg;base64,${response.data.picture}`;
                }
            } catch (error) {
                console.error('An error occurred while fetching data: ', error);
            }
        },
        triggerFileInput() {
            this.$refs.fileInput.click();
        },
        async uploadProfilePicture() {
            const fileInput = this.$refs.fileInput;
            if (fileInput.files.length > 0) {
                const file = fileInput.files[0];
                const formData = new FormData();
                formData.append('username', this.username);
                formData.append('picture', file);

                try {
                    await axios.post('http://localhost:8080/profile/saveProfilePicture', formData, {
                        headers: {
                            'Content-Type': 'multipart/form-data',
                        },
                    });
                    this.profilePictureUrl = URL.createObjectURL(file);
                    alert('Profile picture uploaded successfully');
                } catch (error) {
                    console.error('An error occurred while uploading the profile picture:', error);
                    alert('Failed to upload profile picture');
                }
            }
        },
    },
    mounted() {
        this.getByUsername();
    }
};
</script>




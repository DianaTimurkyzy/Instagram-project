<template>
    <div class="archive-content">
        <Sidebar/>
        <router-link :to="`/${username}`" class="archive-title"><p> ← Archive</p></router-link>
        <div class="photo-grid-archive">
            <img
                    v-for="post in archivedPosts" :key="post.id"
                    :src="`data:image/jpeg;base64,${post.media}`" alt="Archived Post"
            />
        </div>
    </div>
</template>

<script>
import axios from "axios";
import './styles/archive.css';
import Sidebar from "./Sidebar.vue";

export default {
    components: {
        Sidebar,
    },
    data() {
        return {
            archivedPosts: [],
            username: '',
        };
    },
    async mounted() {
        try {
            const profileId = localStorage.getItem('profileId');
            this.username = localStorage.getItem('username');
            const response = await axios.get(
                `http://localhost:8080/post/getAllArchived?profileId=${profileId}`, {
                    headers: {
                        'Authorization': 'Basic ' + localStorage.getItem('Authentication')
                    }
                })
            this.archivedPosts = response.data;
        } catch (error) {
            console.error("An error occurred while fetching archived posts:", error);
        }
    },
};
</script>

<style>

</style>

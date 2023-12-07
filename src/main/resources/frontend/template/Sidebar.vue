<template>
    <aside class="sidebar">
        <div class="sidebar-logo">
            <img src=images/5a4e432a2da5ad73df7efe7a.png alt="logo"/>
        </div>
        <div class="side-section"><p>
            <img class="house-icon" src="images/house-icon.jpg" alt="house-icon">Home</p></div>
        <div class="side-section"><p>
            <img class="message-icon" src="images/message-icon.jpg" alt="message-icon">Messages</p></div>
        <div class="profile-picture-sidebar">
            <img :src="profilePicture" alt="Profile Picture"><a>Profile</a></div>
        <div class="side-section"><p>
            <img class="more-icon" src="images/three-horizontal-lines.png" alt="more-icon">More</p></div>
    </aside>
</template>

<script>
import './styles/sidebar.css';
export default {
    name: "Sidebar",
    data() {
        return {
            profilePicture: '',
        };
    },
    async mounted() {
        try {
            this.profilePicture =`data:image/jpeg;base64,` + localStorage.getItem('profile-picture');
        } catch (error) {
            console.error("An error occurred while fetching profile picture:", error);
        }
    },
};
</script>

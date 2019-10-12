<template>
  <div>
    <v-card-text>Add Monitor View</v-card-text>
    <RoomCard></RoomCard>
  </div>
</template>

<script>
  import RoomCard from '../components/RoomCard'

  export default {
    components: {
      RoomCard
    },
  mounted() {
    // TODO remove
    console.log(process.env)
  }
}
</script>
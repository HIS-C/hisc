<template>
  <div>
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
## Helpful Code Snippets

    @GetMapping(value = "/modelSD/{mid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Models>> getModelsById2(@PathVariable("mid") long mid) {
        List<Models> modelData = new ArrayList<Models>();

        modelRepository.findByMid(mid).forEach(modelData::add);

        // checking if the table is empty and returning message
        if (modelData.isEmpty()) {
            // create an empty list with a message as the first element
            List<Models> notFound = new ArrayList<>();
            notFound.add(new Models("Resource not found"));

            // create a response entity with the list and status code 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }

        return ResponseEntity.ok(modelData);
    }

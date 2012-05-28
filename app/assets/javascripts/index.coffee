$ ->
  $.get "/jsontasks", (data) ->
    $.each data, (index, task) ->
      $("#jsontasks").append $("<li>").text task.label